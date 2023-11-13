package com.mitsudoku.readmodel.service;

import com.mitsudoku.event.EventDto;
import com.mitsudoku.event.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RemoveMovieService implements EventService {
    @Override
    public void process(EventDto eventDto) {
        log.info("REMOVE_MOVIE event received");
    }

    @Override
    public EventType eventType() {
        return EventType.REMOVE_MOVIE;
    }
}
