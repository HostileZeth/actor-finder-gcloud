package com.mitsudoku.readmodel.service;

import com.mitsudoku.event.EventDto;
import com.mitsudoku.event.EventType;
import com.mitsudoku.model.movie.CastDto;
import com.mitsudoku.model.movie.MovieDto;
import com.mitsudoku.readmodel.entity.*;
import com.mitsudoku.readmodel.mapping.ActorMapper;
import com.mitsudoku.readmodel.mapping.MovieMapper;
import com.mitsudoku.readmodel.repository.ActorGraphRepository;
import com.mitsudoku.readmodel.repository.ActorRepository;
import com.mitsudoku.readmodel.repository.MovieIntersectionRepository;
import com.mitsudoku.readmodel.repository.MovieRepository;
import com.mitsudoku.readmodel.service.intersection.MovieIntersectionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AddMovieService implements EventService {

    private final ActorGraphRepository actorGraphRepository;
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    @Qualifier("movieIntersectionSqlImpl")
    private final MovieIntersectionService movieIntersectionService;

    @Override
    public void process(EventDto eventDto) {
        log.info("ADD_MOVIE event received");
        ActorGraph graph = actorGraphRepository.findById(eventDto.getGraphId()).orElseThrow(
                EntityNotFoundException::new
        );
        MovieDto movieDto = eventDto.getMovieDto();
        Movie movie = null;
        Optional<Movie> movieOptional = movieRepository.findById(movieDto.getId());
        if (movieOptional.isPresent()) {
            movie = movieOptional.get();
            if (graph.getMovieList().contains(movie)) {
                return; // prevent multiple message delivery
            }
        } else {
            // create movie, then add
            movie = movieMapper.toEntity(movieDto);
            movie.setActors(getActorsList(movieDto.getCreditsDto().getCast()));
            movieRepository.save(movie);
        }
        movieIntersectionService.processMovieIntersection(graph, movie);
        graph.getMovieList().add(movie);
        actorGraphRepository.save(graph);
    }

    private Set<Actor> getActorsList(ArrayList<CastDto> cast) {
        return cast.stream().map(c -> actorRepository.findById((long) c.getId()).orElse(actorMapper.toEntity(c))).collect(Collectors.toSet());
    }

    @Override
    public EventType eventType() {
        return EventType.ADD_MOVIE;
    }
}
