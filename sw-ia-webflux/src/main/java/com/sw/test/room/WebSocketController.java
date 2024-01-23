package com.sw.test.room;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw.test.service.RoomManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/event-emitter")
public class WebSocketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketController.class);

    private RoomManager roomManager = SpringContext.getBean(RoomManager.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final String EVENT_CREATE_ROOM = "event_create_room";
    private final String EVENT_ENTER_ROOM = "event_enter_room";
    private final String EVENT_SHUFFLE_CARDS = "event_shuffle_cards";
    private final String EVENT_DISTRIBUTE_CARDS = "event_distribute_cards";


    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) throws IOException {
        // Get session and WebSocket connection
        session.setMaxIdleTimeout(0);
        LOGGER.info("Get session and WebSocket connection");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        // Handle new messages
        LOGGER.info("Handle new messages -> {}", message );

        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String eventId = jsonNode.get("eventId").textValue();
            String playerName = jsonNode.get("playerName").textValue();
            String roomId = jsonNode.get("roomId").textValue();
            if (EVENT_CREATE_ROOM.equals(eventId)) {
                roomManager.createRoom(playerName, session);
            }
            if (EVENT_ENTER_ROOM.equals(eventId)) {
                roomManager.enterRoom(roomId, playerName, session);
            }
            if (EVENT_SHUFFLE_CARDS.equals(eventId)){
                roomManager.shuffle(roomId);
            }
            if (EVENT_DISTRIBUTE_CARDS.equals(eventId)) {
                roomManager.distribute(roomId);
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
        LOGGER.info("WebSocket connection closes");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        LOGGER.info("Do error handling here");
    }
}
