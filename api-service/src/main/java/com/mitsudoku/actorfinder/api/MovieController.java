package com.mitsudoku.actorfinder.api;

import com.mitsudoku.actorfinder.repository.ActorGraphRepository;
import com.mitsudoku.actorfinder.service.AuthUserService;
import com.mitsudoku.actorfinder.service.CastCompareService;
import com.mitsudoku.actorfinder.service.EventService;
import com.mitsudoku.client.MovieClient;
import com.mitsudoku.exception.ItemNotFoundException;
import com.mitsudoku.event.EventType;
import com.mitsudoku.model.movie.CastDto;
import com.mitsudoku.model.movie.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieClient movieClient;
    private final CastCompareService castCompareService;
    private final ActorGraphRepository actorGraphRepository;

    private final EventService eventService;
    private final AuthUserService authUserService;

    @GetMapping("/cast-intersection")
    public List<CastDto> getCastIntersection(@RequestParam("movie1") String movie1, @RequestParam("movie2") String movie2) {
        MovieDto movieDto1 = movieClient.getMovieWithCast(movie1);
        MovieDto movieDto2 = movieClient.getMovieWithCast(movie2);
        return castCompareService.getCastIntersection(movieDto1, movieDto2);
    }

    @PutMapping("/add/name")
    public void addMovie(@RequestParam("movie") String movie, @RequestParam("graphId") UUID graphId) {
        if (!actorGraphRepository.existsById(graphId)) {
            throw new ItemNotFoundException("Failed to find graph with id = " + graphId);
        }
        // TODO validate event
        MovieDto movieWithCast = movieClient.getMovieWithCast(movie);
        eventService.processMovieEvent(movieWithCast, (long) movieWithCast.getId(), graphId, EventType.ADD_MOVIE, authUserService.getUserId());
    }

    @DeleteMapping("/remove/id")
    public void removeMovie(@RequestParam("movieId") Long movieId, @RequestParam("graphId") UUID graphId) {
        if (!actorGraphRepository.existsById(graphId)) {
            throw new ItemNotFoundException("Failed to find graph with id = " + graphId);
        }
        // TODO validate event
        eventService.processMovieEvent(null, movieId, graphId, EventType.REMOVE_MOVIE, authUserService.getUserId());
    }

}
