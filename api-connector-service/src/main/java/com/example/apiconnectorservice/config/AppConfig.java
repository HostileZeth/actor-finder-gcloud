package com.example.apiconnectorservice.config;

import com.mitsudoku.config.FeignConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignConfig.class)
public class AppConfig {

}
