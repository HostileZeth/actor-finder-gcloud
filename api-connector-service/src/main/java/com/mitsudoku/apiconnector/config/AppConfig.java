package com.mitsudoku.apiconnector.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitsudoku.config.FeignConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignConfig.class)
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
