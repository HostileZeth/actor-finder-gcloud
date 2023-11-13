package com.mitsudoku.readmodel.mapping;

import com.mitsudoku.model.movie.MovieDto;
import com.mitsudoku.readmodel.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "actors", source = "creditsDto.cast")
    Movie toEntity(MovieDto movieDto);
}
