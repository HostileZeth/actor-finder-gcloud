package com.mitsudoku.readmodel.service.intersection;

import com.mitsudoku.readmodel.entity.ActorGraph;
import com.mitsudoku.readmodel.entity.Movie;
import com.mitsudoku.readmodel.entity.MovieIntersectionResultDto;

import java.util.List;

public interface MovieIntersectionService {
    List<MovieIntersectionResultDto> processMovieIntersection(ActorGraph graph, Movie movie);
}
