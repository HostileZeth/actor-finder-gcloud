package com.mitsudoku.actorfinder.service;

import com.mitsudoku.actorfinder.entity.Event;
import com.mitsudoku.actorfinder.repository.EventRepository;
import com.mitsudoku.exception.EventValidationFailed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventValidationService {

    private final EventRepository eventRepository;

    public void validate(Event event) {
        switch (event.getEventType()) {
            case ADD_MOVIE -> validateAddMovie(event);
            case REMOVE_MOVIE -> validateRemoveMovie(event);
            case CREATE_GRAPH -> validateCreateGraph(event);
        }
    }

    private void validateAddMovie(Event event) {
        if (eventRepository.isMovieAlreadyAdded(event.getGraphId(), event.getMovieId())) {
            throw new EventValidationFailed("Failed to add a movie - Movie already added to the graph");
        }
    }

    private void validateRemoveMovie(Event event) {
        if (!eventRepository.isMovieAlreadyAdded(event.getGraphId(), event.getMovieId())) {
            throw new EventValidationFailed("Failed to add a movie - Movie not added to the graph");
        }
    }

    private void validateCreateGraph(Event event) {
        // validated by db constraints
    }
}
