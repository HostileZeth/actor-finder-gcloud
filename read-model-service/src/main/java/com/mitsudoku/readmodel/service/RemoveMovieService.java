package com.mitsudoku.readmodel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitsudoku.event.EventDto;
import com.mitsudoku.event.EventType;
import com.mitsudoku.readmodel.entity.Movie;
import com.mitsudoku.readmodel.model.ws.WebSocketMessageDto;
import com.mitsudoku.readmodel.repository.ActorGraphRepository;
import com.mitsudoku.readmodel.repository.MovieRepository;
import com.mitsudoku.readmodel.websocket.GraphStateWsHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class RemoveMovieService implements EventService {

    private final MovieRepository movieRepository;
    private final ActorGraphRepository actorGraphRepository;
    private final ObjectMapper objectMapper;
    @Override
    @Transactional
    public void process(EventDto eventDto) {
        log.info("REMOVE_MOVIE event received");
        Long movieId = eventDto.getMovieId();
        UUID graphId = eventDto.getGraphId();
        Movie movie = movieRepository.findById(movieId).orElseThrow(EntityNotFoundException::new);
        actorGraphRepository.findById(graphId).orElseThrow(EntityNotFoundException::new)
                        .getMovieList().remove(movie);
        if (movieRepository.countGraphs(movieId) == 0) {
            // maybe get rid of fully removing movie info
            movieRepository.deleteById(movieId);
        }

        try {
            GraphStateWsHandler.send(graphId,
                    objectMapper.writeValueAsString(WebSocketMessageDto.ofRemoveMovie(movieId)));
        } catch (JsonProcessingException e) {
            GraphStateWsHandler.send(graphId, "Failed to send ADD_MOVIE message, movieId = " + movieId);
        }

    }

    @Override
    public EventType eventType() {
        return EventType.REMOVE_MOVIE;
    }
}
