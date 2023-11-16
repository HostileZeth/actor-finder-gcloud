package com.mitsudoku.readmodel.service.intersection;

import com.mitsudoku.readmodel.entity.ActorGraph;
import com.mitsudoku.readmodel.entity.Movie;
import com.mitsudoku.readmodel.entity.MovieIntersectionResultDto;
import com.mitsudoku.readmodel.repository.MovieIntersectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieIntersectionSqlImpl implements MovieIntersectionService {
    private final MovieIntersectionRepository movieIntersectionRepository;
    public void processMovieIntersection(ActorGraph graph, Movie movie) {
        List<MovieIntersectionResultDto> movieIntersections = movieIntersectionRepository.getMovieIntersections(movie.getId(),
                graph.getId());
        movieIntersections.forEach(mi -> movieIntersectionRepository.putMovieIntersections(movie.getId(), mi.getMovieId(), mi.getActorId(), graph.getId()));
    }
}
