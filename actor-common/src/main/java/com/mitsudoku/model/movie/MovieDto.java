package com.mitsudoku.model.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovieDto {

    private boolean adult;
    private String backdropPath;
    private ArrayList<Integer> genreIds;
    private int id;
    private ArrayList<String> originCountry;
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

    private MovieCreditsDto creditsDto;

}
