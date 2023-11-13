package com.mitsudoku.readmodel.entity;

import com.mitsudoku.readmodel.util.StringListConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Movie {
    @Id
    private Long id; // fill with api output id

    private boolean adult;
    private String backdropPath;

    private String originalLanguage;
    private String originalTitle;
    private String releaseDate;
    private String title;
    private String originalName;
    private String overview;
    private double popularity;
    private String posterPath;
    private String firstAirDate;
    private String name;
    private String video;
    private double voteAverage;
    private int voteCount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();
}
