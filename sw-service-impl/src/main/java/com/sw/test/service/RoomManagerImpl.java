package com.sw.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw.test.config.CardInitConfig;
import com.sw.test.dto.RoomDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
public class RoomManagerImpl implements RoomManager{

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomManagerImpl.class);

    @Autowired
    private CardInitConfig config;

    @Autowired
    private CardService cardService;

    private Map<String, Room> roomMap = new ConcurrentHashMap<>();

    private static final ObjectMapper json = new ObjectMapper();


    public List<RoomDTO> availableRooms(){

        List<RoomDTO> availableRooms = new ArrayList<>();
        roomMap.forEach((id, room) -> {
            if (isRoomAvailable(room)) {
                RoomDTO thisRoom = new RoomDTO(id, room.getName());
                availableRooms.add(thisRoom);
            }
        });


        return availableRooms;
    }

    /**
     *  Create a room for play cards.
     * @param playerName
     * @param session
     */
    public void createRoom(String playerName, Session session){

        List<String> cardsLibrary = new ArrayList<>();
        cardsLibrary.addAll(config.getLibrary());

        List<Player> players = new ArrayList<>();
        if (playerName.isEmpty()) {
            playerName = "Anonymous";
        }
        players.add(new Player("1", playerName, new ArrayList<>(), session));
        players.add(new Player("2", "RobotA", new ArrayList<>(), null));
        players.add(new Player("3", "RobotB", new ArrayList<>(), null));
        players.add(new Player("4", "RobotC", new ArrayList<>(), null));

        String roomId = UUID.randomUUID().toString();
        Room room = new Room(roomId, playerName+"'s room", "", cardsLibrary, players);

        sendRoom(session, room);

        roomMap.put(roomId, room);
    }

    /**
     *  Enter an available room.
     * @param roomId
     * @param playerName
     * @param session
     */
    public void enterRoom(String roomId, String playerName, Session session){
        Room room = roomMap.get(roomId);
        if (room != null && isRoomAvailable(room)) {
            room.getPlayers().stream().anyMatch(player -> {
                if (player.getSession() == null) {
                    player.setName(playerName);
                    player.setSession(session);

                    sendRoom(session, room);

                    return true;
                }
                return false;
            });
        }




    }

    /**
     *  Shuffle cards of a room.
     * @param roomId
     */
    public void shuffle(String roomId){

        Room room = roomMap.get(roomId);

        room.getPlayers().forEach(player -> { player.getCards().clear(); });
        room.getDeck().clear();
        List<String> cardsLibrary = new ArrayList<>();
        cardsLibrary.addAll(config.getLibrary());
        room.setDeck(cardsLibrary);
        Collections.shuffle(room.getDeck());


        List<Session> sessions = room.getPlayers().stream().map(player -> player.getSession()).collect(Collectors.toList());

        sessions.forEach(session -> {
            sendRoom(session, room);
        });
    }

    /**
     *
     *  Distribute cards from Deck to Players, and publisher the Winner.
     *
     * @param roomId
     * @return
     */
    public void distribute(String roomId){

        Room room = roomMap.get(roomId);

        if (room.getDeck().isEmpty()) {
            this.shuffle(roomId);
        }

        List<Session> sessions = room.getPlayers().stream().map(player -> player.getSession()).collect(Collectors.toList());

        Iterator<Player> playerIterator = room.getPlayers().iterator();

        while (!room.getDeck().isEmpty()) {
            Player player;
            if (!playerIterator.hasNext()) {
                playerIterator = room.getPlayers().iterator();
            }
            player = playerIterator.next();
            player.getCards().add(room.getDeck().get(0));
            room.getDeck().remove(0);

            sessions.forEach(session -> {
                sendRoom(session, room);
            });

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String winner  = findWinner(room);
        room.setWinner(winner);

        sessions.forEach(session -> {
            sendRoom(session, room);
        });
    }

    private boolean isRoomAvailable(Room room) {
        return room.getPlayers().stream().anyMatch(player -> {
            Session session = player.getSession();
            if (session != null && session.isOpen()){
                return true;
            }
            return false;
        });
    }

    private void sendRoom(Session session, Room room){
        try {
            if (session != null && session.isOpen()) {
                session.getBasicRemote().sendText(json.writeValueAsString(room));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String findWinner(Room room) {
        String winnerName = "";
        List<String> winnerCards = new ArrayList<>();

        for (Player player : room.getPlayers()) {
            LOGGER.info("player:"+player.getName()+" cards:"+player.getCards());
            List<String> largestAndMostCards = cardService.alphanumericWinRule(player.getCards());
            LOGGER.info("largest and most:" + largestAndMostCards);
            LOGGER.info("winner cards:" + winnerCards);
            if (cardService.compareTo(largestAndMostCards, winnerCards)) {
                winnerCards = largestAndMostCards;
                winnerName = player.getName();
            }
        }

        return winnerName;

    }

}
