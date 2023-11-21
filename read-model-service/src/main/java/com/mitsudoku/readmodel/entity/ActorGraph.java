package com.mitsudoku.readmodel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ActorGraph extends AuditedEntity {
    @Id
    private UUID id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "graph_movie",
            joinColumns = @JoinColumn(name = "graph_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movieList = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "graph_id")
    private List<MovieIntersection> movieIntersections = new ArrayList<>();

}
