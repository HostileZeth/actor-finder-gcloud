package com.mitsudoku.actorfinder.config;

import feign.Logger;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Data
@Configuration
@EnableFeignClients(basePackages = {"com.mitsudoku.client"})
@EnableConfigurationProperties(WeatherProperties.class)
public class CloudConfig {

    @Value("${cloud.random.number}")
    private int randomNumber;
}
