package com.mitsudoku.readmodel.model;


import com.mitsudoku.readmodel.entity.Movie;
import com.mitsudoku.readmodel.entity.MovieIntersection;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class ActorGraphDto {
    private UUID id;

    private List<MovieDto> movieList = new ArrayList<>();

    private List<MovieIntersectionDto> movieIntersections = new ArrayList<>();
}
