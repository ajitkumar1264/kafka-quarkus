package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@ServerEndpoint("/ws/messages")
public class websocket {

    private static final Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("New session added: " + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Session removed: " + session.getId());
    }

    public static void broadcastMessage(String message) {
        System.out.println("Broadcasting message: " + message);
        for (Session session : sessions) {
            if (session.isOpen()) {
                session.getAsyncRemote().sendText(message);
            }
        }
    }
}