package com.mitsudoku.readmodel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieIntersectionResultImpl implements MovieIntersectionResultDto{
    private Long movieId;
    private Long actorId;
}
