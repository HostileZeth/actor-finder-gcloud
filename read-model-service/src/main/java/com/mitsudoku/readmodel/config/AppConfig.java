package com.mitsudoku.readmodel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mitsudoku.event.EventType;
import com.mitsudoku.readmodel.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public Map<EventType, EventService> serviceMap(@Autowired List<EventService> services) {
        Map<EventType, EventService> serviceMap = new HashMap<>();
        services.forEach(s -> serviceMap.put(s.eventType(), s));
        return serviceMap;
    }
}
