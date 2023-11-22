package com.mitsudoku.readmodel.mapping;

import com.mitsudoku.readmodel.entity.MovieIntersection;
import com.mitsudoku.readmodel.model.MovieIntersectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ActorMapper.class })
public interface MovieIntersectionMapper {

    @Mapping(target = "movieId", source = "movie.id")
    @Mapping(target = "movie2id", source = "movie2.id")
    MovieIntersectionDto toDto(MovieIntersection entity);
}
