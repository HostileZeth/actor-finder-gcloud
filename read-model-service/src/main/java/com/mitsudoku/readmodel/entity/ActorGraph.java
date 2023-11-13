package com.mitsudoku.readmodel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ActorGraph extends AuditedEntity {
    @Id
    private UUID id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "graph_movie",
            joinColumns = @JoinColumn(name = "graph_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movieList = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "graph_id")
    private List<MovieIntersection> movieIntersections = new ArrayList<>();

}
