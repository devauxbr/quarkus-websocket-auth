package org.acme.ws;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.security.Principal;

/**
 * Simple WebSocket Endpoint that sends back the authenticated username, or null
 */
@ServerEndpoint(
        value = "/ws"
)
@Slf4j
public class AuthWebSocket {
    @OnOpen
    public void open(Session session) {
        Principal principal = session.getUserPrincipal();
        if (principal == null) {
            session.getAsyncRemote().sendText("null");
        } else {
            session.getAsyncRemote().sendText(principal.getName());
        }
    }
}
