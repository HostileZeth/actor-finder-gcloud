//package com.mitsudoku.readmodel.websocket;
//
//import jakarta.websocket.OnClose;
//import jakarta.websocket.OnOpen;
//import jakarta.websocket.Session;
//import jakarta.websocket.server.ServerEndpoint;
//
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
//@ServerEndpoint("/push")
//public class Push {
//
//    private static final Set<Session> SESSIONS = ConcurrentHashMap.newKeySet();
//
//    @OnOpen
//    public void onOpen(Session session) {
//        SESSIONS.add(session);
//    }
//
//    @OnClose
//    public void onClose(Session session) {
//        SESSIONS.remove(session);
//    }
//
//    public static void sendAll(String text) {
//        synchronized (SESSIONS) {
//            for (Session session : SESSIONS) {
//                if (session.isOpen()) {
//                    session.getAsyncRemote().sendText(text);
//                }
//            }
//        }
//    }
//
//}
