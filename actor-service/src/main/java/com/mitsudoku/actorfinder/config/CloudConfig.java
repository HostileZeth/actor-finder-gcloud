package com.mitsudoku.actorfinder.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class CloudConfig {

    @Value("${cloud.random.number}")
    private int randomNumber;
}
