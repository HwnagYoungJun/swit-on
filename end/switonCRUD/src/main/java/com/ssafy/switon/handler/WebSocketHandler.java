package com.ssafy.switon.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import io.jsonwebtoken.io.IOException;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

    // 클라이언트와 연결 이후에 실행되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        System.out.println("연결됨 : " + session.getId());
    }

    // 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(session.getId() + " : " + message.getPayload());
        for (WebSocketSession sess : sessionList) {
            sess.sendMessage(new TextMessage(session.getId() + " : " + message.getPayload()));
        }
    }

    // 클라이언트와 연결을 끊었을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        System.out.println("연결 끊김 : " + session.getId());
    }

    public void send(String message) throws java.io.IOException {
        for (WebSocketSession sess : sessionList) {
            try {
                sess.sendMessage(new TextMessage("서버 : " + message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}