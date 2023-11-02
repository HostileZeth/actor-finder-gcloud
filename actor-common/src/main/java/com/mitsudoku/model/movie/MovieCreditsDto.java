package com.mitsudoku.model.movie;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class MovieCreditsDto {

    public int id;
    public ArrayList<CastDto> cast;
    public ArrayList<CrewDto> crew;

}
