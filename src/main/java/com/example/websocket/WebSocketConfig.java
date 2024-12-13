package com.example.websocket;

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
        // 클라이언트는 설정된 접두사가 붙은 경로로 메시지를 전송한다. -> /app/**
        // 클라이언트가 /app/** 로 메시지를 전송하게 되면 컨트롤러의 @MessageMapping에 의해 처리된다.
        registry.setApplicationDestinationPrefixes("/app");

        // 브로커는 클라이언트가 구독한 경로 (/queue 또는 /topic)에 메시지를 전달한다.
        // 클라이언트는 이 경로를 미리 구독해야 한다.
        // @SendTo 에 의해 처리된다.
        registry.enableSimpleBroker("/queue", "/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket");
    }
}
