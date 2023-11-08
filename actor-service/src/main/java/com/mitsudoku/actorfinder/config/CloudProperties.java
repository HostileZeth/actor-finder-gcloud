package com.mitsudoku.actorfinder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("cloud")
public class CloudProperties {

    private int randomNumber;
}
