package com.mitsudoku.apiconnector.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class MovieDto extends ResultDto {

    public boolean adult;
    public String backdrop_path;
    public ArrayList<Integer> genre_ids;
    public int id;
    public ArrayList<String> origin_country;
    public String original_language;
    public String original_title;
    public String release_date;
    public String title;
    public String original_name;
    public String overview;
    public double popularity;
    public String poster_path;
    public String first_air_date;
    public String name;
    public String video;
    public double vote_average;
    public int vote_count;

}
