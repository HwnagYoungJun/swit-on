package com.ssafy.switon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//클라이언트로 메세지를 응답해줄 때 prefix를 정의
		registry.enableSimpleBroker("/topic");
		//클라이언트에서 메세지 송신 시 붙여줄 prefix를 정의
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//최초 소켓 연결을 하는 경우, endpoint가 되는 url 추후 javascript에서 SockJS 생성자를 통해 연결
		registry.addEndpoint("/websocket").withSockJS();
	}
}