package com.mitsudoku.model.event;

import com.mitsudoku.model.movie.MovieDto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EventDto {
    private Long id;
    private EventType eventType;
    private UUID graphId;
    private MovieDto movieDto;
}
