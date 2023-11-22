package com.mitsudoku.readmodel.mapping;

import com.mitsudoku.model.movie.CastDto;
import com.mitsudoku.readmodel.entity.Actor;
import com.mitsudoku.readmodel.model.ActorDto;
import com.mitsudoku.readmodel.util.ImageUrlUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ImageUrlUtil.class })
public interface ActorMapper {
    Actor toEntity(CastDto castDto);

    @Mapping(target = "profilePath", source = "profilePath", qualifiedByName = {"ImageUrlUtil", "processUrl"})
    ActorDto toDto(Actor entity);
}
