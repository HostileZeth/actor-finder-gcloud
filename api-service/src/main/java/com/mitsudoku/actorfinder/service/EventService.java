package com.mitsudoku.actorfinder.service;

import com.mitsudoku.actorfinder.entity.ActorGraph;
import com.mitsudoku.actorfinder.entity.Event;
import com.mitsudoku.actorfinder.mapping.EventMapper;
import com.mitsudoku.actorfinder.pubsub.EventProducer;
import com.mitsudoku.actorfinder.repository.EventRepository;
import com.mitsudoku.event.EventType;
import com.mitsudoku.model.movie.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EventService {

    private final EventProducer eventProducer;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final EventValidationService eventValidationService;

    @Transactional
    public void processMovieEvent(MovieDto movieWithCast, Long movieId, UUID graphId, EventType eventType, UUID userId) {
        Event event = new Event();
        event.setGraphId(graphId);
        event.setEventType(eventType);
        event.setMovieId(movieId);
        event.setCreatedUser(userId);
        eventValidationService.validate(event);
        eventRepository.saveAndFlush(event);
        eventProducer.sendEvent(eventMapper.toMovieEvent(event, movieWithCast));
    }

    @Transactional
    public void processGraphEvent(ActorGraph actorGraph, UUID userId) {
        Event event = new Event();
        event.setEventType(EventType.CREATE_GRAPH);
        event.setGraphId(actorGraph.getId());
        event.setCreatedUser(userId);
        eventValidationService.validate(event);
        eventRepository.saveAndFlush(event);
        eventProducer.sendEvent(eventMapper.toActorGraphEvent(event));
    }
}
