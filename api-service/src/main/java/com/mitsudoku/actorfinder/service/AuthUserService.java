package com.mitsudoku.actorfinder.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthUserService {
    static UUID userId = new UUID(0,0); //TODO users auth

    public UUID getUserId() {
        return userId;
    }
}
