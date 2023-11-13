package com.mitsudoku.event;

import com.fasterxml.jackson.annotation.Nulls;
import com.mitsudoku.model.movie.MovieDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private Long id;
    private EventType eventType;
    private UUID graphId;
    private MovieDto movieDto;
    private UUID createdUser;
    private Instant createStamp;
}
