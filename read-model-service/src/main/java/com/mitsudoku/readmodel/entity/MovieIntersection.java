package com.mitsudoku.readmodel.entity;


import lombok.*;

import jakarta.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
// TODO create sql script / changelog
public class MovieIntersection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_intersection_seq_gen")
    @SequenceGenerator(
            name = "movie_intersection_seq_gen",
            sequenceName = "movie_intersection_seq",
            allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie2_id")
    private Movie movie2;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;
}
