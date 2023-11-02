package com.mitsudoku.actorfinder.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class MovieIntersection {

    @Id
    private Long id;

    @ManyToOne
    private Movie movie1;

    @ManyToOne
    private Movie movie2;

    @ManyToOne
    private Actor actor;
}
