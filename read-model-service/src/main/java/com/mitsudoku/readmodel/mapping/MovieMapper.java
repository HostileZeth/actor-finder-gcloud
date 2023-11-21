package com.mitsudoku.readmodel.mapping;

import com.mitsudoku.model.movie.MovieDto;
import com.mitsudoku.readmodel.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie toEntity(MovieDto movieDto);

    com.mitsudoku.readmodel.model.MovieDto toDto(Movie movie);
}
