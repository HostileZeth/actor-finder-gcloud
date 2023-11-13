package com.mitsudoku.readmodel.service;

import com.mitsudoku.event.EventDto;
import com.mitsudoku.event.EventType;

public interface EventService {

    void process(EventDto eventDto);

    EventType eventType();

}
