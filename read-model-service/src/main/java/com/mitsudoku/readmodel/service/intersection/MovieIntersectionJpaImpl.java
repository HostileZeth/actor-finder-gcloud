package com.mitsudoku.readmodel.service.intersection;

import com.mitsudoku.readmodel.entity.*;
import com.mitsudoku.readmodel.model.MovieIntersectionDto;
import com.mitsudoku.readmodel.repository.MovieIntersectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieIntersectionJpaImpl implements MovieIntersectionService {

    public List<MovieIntersectionResultDto> processMovieIntersection(ActorGraph actorGraph, Movie movie) {
        Set<Movie> movies = actorGraph.getMovieList();
        List<MovieIntersection> intersections = new ArrayList<>();
        movies.forEach(m -> m.getActors().forEach(a -> {
                    if (movie.getActors().contains(a)) {
                        intersections.add(MovieIntersection.builder()
                                .movie(movie)
                                .movie2(m)
                                .actor(a)
                                .build());
                        movie.getActors().remove(a); // remove duplicate actor entity
                        movie.getActors().add(a); // add existing actor entity
                    }
                }
        ));
        actorGraph.getMovieIntersections().addAll(intersections);
        return intersections.stream().map(i -> new MovieIntersectionResultImpl(i.getMovie2().getId(), i.getActor().getId())).collect(Collectors.toList());
    }
}
