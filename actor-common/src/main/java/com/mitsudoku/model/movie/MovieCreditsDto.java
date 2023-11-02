package com.mitsudoku.model.movie;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class MovieCreditsDto {

    private int id;
    private ArrayList<CastDto> cast;
    private ArrayList<CrewDto> crew;

}
