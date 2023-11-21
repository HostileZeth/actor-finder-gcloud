package com.mitsudoku.readmodel.model;

import com.mitsudoku.readmodel.entity.Actor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class MovieDto {
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

    private List<Actor> actors = new ArrayList<>();
}
