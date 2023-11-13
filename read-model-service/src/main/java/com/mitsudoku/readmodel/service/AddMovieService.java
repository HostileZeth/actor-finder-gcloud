package com.mitsudoku.readmodel.service;

import com.mitsudoku.event.EventDto;
import com.mitsudoku.event.EventType;
import com.mitsudoku.model.movie.MovieDto;
import com.mitsudoku.readmodel.entity.ActorGraph;
import com.mitsudoku.readmodel.entity.Movie;
import com.mitsudoku.readmodel.entity.MovieIntersection;
import com.mitsudoku.readmodel.mapping.MovieMapper;
import com.mitsudoku.readmodel.repository.ActorGraphRepository;
import com.mitsudoku.readmodel.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AddMovieService implements EventService {

    private final ActorGraphRepository actorGraphRepository;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

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
        } else {
            // create movie, then add
            movie = movieMapper.toEntity(movieDto);
            movieRepository.save(movie);
        }
        graph.getMovieIntersections().addAll(processIntersections(graph.getMovieList(), movie));
        graph.getMovieList().add(movie);
        actorGraphRepository.save(graph);
    }

    private List<MovieIntersection> processIntersections(Set<Movie> movies, Movie movie) {
        List<MovieIntersection> intersections = new ArrayList<>();
        movies.forEach(m -> movie.getActors().forEach(a -> {
                    if (m.getActors().contains(a)) {
                        intersections.add(MovieIntersection.builder()
                                .movie(movie)
                                .movie2(m)
                                .actor(a)
                                .build());
                    }
                }
        ));
        return intersections;
    }

    @Override
    public EventType eventType() {
        return EventType.ADD_MOVIE;
    }
}
