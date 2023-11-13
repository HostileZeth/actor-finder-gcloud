package com.mitsudoku.readmodel.actorservice;

import com.mitsudoku.actorfinder.ApiService;
import com.mitsudoku.actorfinder.api.DebugController;
import com.mitsudoku.actorfinder.config.WeatherProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ApiService.class, DebugController.class, WeatherProperties.class})
class ActorServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}