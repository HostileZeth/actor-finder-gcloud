package com.mitsudoku.readmodel.mapping;

import com.mitsudoku.model.movie.MovieDto;
import com.mitsudoku.readmodel.entity.Movie;
import com.mitsudoku.readmodel.util.ImageUrlUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ActorMapper.class, ImageUrlUtil.class })
public interface MovieMapper {

    Movie toEntity(MovieDto movieDto);

    @Mapping(target = "backdropPath", source = "backdropPath", qualifiedByName = {"ImageUrlUtil", "processUrl"})
    @Mapping(target = "posterPath", source = "posterPath", qualifiedByName = {"ImageUrlUtil", "processUrl"})
    com.mitsudoku.readmodel.model.MovieDto toDto(Movie movie);
}
