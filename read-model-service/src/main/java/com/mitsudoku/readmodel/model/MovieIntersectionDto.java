package com.mitsudoku.readmodel.model;

import com.mitsudoku.readmodel.entity.Actor;
import com.mitsudoku.readmodel.entity.Movie;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieIntersectionDto {
    private Long id;

    private Long movieId;

    private Long movie2id;

    private ActorDto actor;
}
