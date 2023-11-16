package com.mitsudoku.readmodel.service.intersection;

import com.mitsudoku.readmodel.entity.ActorGraph;
import com.mitsudoku.readmodel.entity.Movie;

public interface MovieIntersectionService {
    void processMovieIntersection(ActorGraph graph, Movie movie);
}
