package com.mitsudoku.readmodel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitsudoku.readmodel.repository.ActorGraphRepository;
import com.mitsudoku.readmodel.websocket.GraphStateWsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("Registering /socket handler");
        registry.addHandler(socketHandler(),"/socket").setAllowedOrigins("*");
//                .withSockJS();
    }

    @Bean
    public WebSocketHandler socketHandler() {
        return new GraphStateWsHandler(
                applicationContext.getBean(ActorGraphRepository.class),
                applicationContext.getBean(ObjectMapper.class)
        );
    }

}
