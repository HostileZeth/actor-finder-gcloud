package com.mitsudoku.actorservice;

import com.mitsudoku.actorfinder.ActorFinderApplication;
import com.mitsudoku.actorfinder.api.ActorAnalyzeController;
import com.mitsudoku.actorfinder.config.CloudConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ActorFinderApplication.class, ActorAnalyzeController.class})
class ActorServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
