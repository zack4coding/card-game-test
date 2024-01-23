package com.sw.test.service;

import com.sw.test.dto.RoomDTO;

import javax.websocket.*;

import java.util.List;

public interface RoomManager {

    List<RoomDTO> availableRooms();

    /**
     *  Create a room for play cards.
     * @param playerName
     * @param session
     */
    void createRoom(String playerName, Session session);

    /**
     *  Enter an available room.
     * @param roomId
     * @param playerName
     * @param session
     */
    void enterRoom(String roomId, String playerName, Session session);

    /**
     *  Shuffle cards of a room.
     * @param roomId
     */
    void shuffle(String roomId);

    /**
     *
     *  Distribute cards from Deck to Players, and publisher the Winner.
     *
     * @param roomId
     * @return
     */
    void distribute(String roomId);

}
