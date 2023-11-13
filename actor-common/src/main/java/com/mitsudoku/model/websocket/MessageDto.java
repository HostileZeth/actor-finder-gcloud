package com.mitsudoku.model.websocket;

import com.mitsudoku.event.EventType;
import com.mitsudoku.model.movie.MovieDto;
import lombok.Data;

import java.util.List;

@Data
public class MessageDto {

    private EventType eventType;
    private MovieDto movieDto;
    private List<IntersectionDto> intersections;
}
