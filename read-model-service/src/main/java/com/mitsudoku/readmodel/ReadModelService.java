package com.mitsudoku.readmodel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.mitsudoku.readmodel.entity")
public class ReadModelService
{
    public static void main(String[] args) {
        SpringApplication.run(ReadModelService.class, args);
    }
}
