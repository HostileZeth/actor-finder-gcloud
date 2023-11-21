package com.mitsudoku.readmodel.websocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitsudoku.readmodel.model.ws.WebSocketMessageDto;
import com.mitsudoku.readmodel.repository.ActorGraphRepository;
import jakarta.websocket.server.ServerEndpoint;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ServerEndpoint(value = "/graph-state")
@Slf4j
@RequiredArgsConstructor
public class GraphStateWsHandler extends TextWebSocketHandler {

    private final ActorGraphRepository actorGraphRepository;
    private final ObjectMapper objectMapper;

    private static final Set<WebSocketSession> SESSIONS = ConcurrentHashMap.newKeySet();
    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, TextMessage message) throws IOException {
        String graphId = message.getPayload();
        if (!actorGraphRepository.existsById(UUID.fromString(graphId))) {
            throw new EntityNotFoundException();
        }
        log.info("Someone subscribed to graph with id: {}", graphId);
        session.getAttributes().put("graph-id", graphId);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(
                WebSocketMessageDto.ofMessage("You subscribed to the " + graphId))));
        SESSIONS.add(session);
    }

    //send graph update via JSON as text
    public static void send(UUID graphId, String message) {
        Set<WebSocketSession> userSessions;

        synchronized(SESSIONS) {
            userSessions = SESSIONS.stream()
                    .filter(s -> s.getAttributes().get("graph-id").equals(graphId.toString()))
                    .collect(Collectors.toSet());
        }

        try {
            for (WebSocketSession userSession : userSessions) {
                if (userSession.isOpen()) {
                    userSession.sendMessage(new TextMessage(message));
                }
            }
        } catch (IOException e) {
            log.error("Failed to send WS message :");
            log.error(message);
        }
    }

}
