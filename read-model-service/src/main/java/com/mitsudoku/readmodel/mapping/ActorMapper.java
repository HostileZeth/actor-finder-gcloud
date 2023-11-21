package com.mitsudoku.readmodel.mapping;

import com.mitsudoku.model.movie.CastDto;
import com.mitsudoku.readmodel.entity.Actor;
import com.mitsudoku.readmodel.model.ActorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    Actor toEntity(CastDto castDto);

    ActorDto toDto(Actor entity);
}
