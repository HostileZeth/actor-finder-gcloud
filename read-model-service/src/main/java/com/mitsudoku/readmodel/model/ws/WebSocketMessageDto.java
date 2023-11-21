package com.mitsudoku.readmodel.model.ws;

import com.mitsudoku.readmodel.entity.MovieIntersectionResultDto;
import com.mitsudoku.readmodel.model.MovieDto;
import com.mitsudoku.readmodel.model.MovieIntersectionDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Data
public class WebSocketMessageDto {
    private SocketEventType socketEventType;
    private Long movieId;
    private MovieDto movie;
    private List<MovieIntersectionResultDto> intersections;
    private String message;

    public static WebSocketMessageDto ofMessage(String message) {
        WebSocketMessageDto dto = new WebSocketMessageDto();
        dto.setMessage(message);
        dto.setSocketEventType(SocketEventType.DEBUG);
        return dto;
    }

    public static WebSocketMessageDto ofAddMovie(MovieDto movieDto, List<MovieIntersectionResultDto> intersections) {
        WebSocketMessageDto dto = new WebSocketMessageDto();
        dto.setMovie(movieDto);
        dto.setIntersections(intersections);
        dto.setSocketEventType(SocketEventType.ADD_MOVIE);
        return dto;
    }

    public static WebSocketMessageDto ofRemoveMovie(Long movieId) {
        WebSocketMessageDto dto = new WebSocketMessageDto();
        dto.setMovieId(movieId);
        dto.setSocketEventType(SocketEventType.REMOVE_MOVIE);
        return dto;
    }

    public enum SocketEventType {
        DEBUG,
        ADD_MOVIE,
        REMOVE_MOVIE
    }
}
