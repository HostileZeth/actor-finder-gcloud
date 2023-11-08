package com.mitsudoku.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EventValidationFailed extends IllegalArgumentException{
    public EventValidationFailed(String message) {
        super(message);
    }
}
