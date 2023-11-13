package com.mitsudoku.actorfinder.mapping;


import com.mitsudoku.actorfinder.entity.Event;
import com.mitsudoku.event.EventDto;
import com.mitsudoku.model.movie.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "movieDto", source = "movieWithCast")
    EventDto toMovieEvent(Event event, MovieDto movieWithCast);

    @Mapping(target = "id", source = "event.id")
    @ValueMapping(target = "eventType", source = "CREATE_GRAPH")
    EventDto toActorGraphEvent(Event event);
}
