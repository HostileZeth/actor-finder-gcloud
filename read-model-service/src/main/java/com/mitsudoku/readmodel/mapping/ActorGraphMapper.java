package com.mitsudoku.readmodel.mapping;

import com.mitsudoku.readmodel.entity.ActorGraph;
import com.mitsudoku.readmodel.model.ActorGraphDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        ActorMapper.class,
        MovieMapper.class,
        MovieIntersectionMapper.class
})
public interface ActorGraphMapper {
    ActorGraphDto toDto(ActorGraph entity);
}
