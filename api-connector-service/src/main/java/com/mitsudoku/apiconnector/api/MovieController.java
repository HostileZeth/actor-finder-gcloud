package com.mitsudoku.apiconnector.api;

import com.mitsudoku.apiconnector.client.MovieDbClient;
import com.mitsudoku.apiconnector.exception.ItemNotFoundException;
import com.mitsudoku.model.movie.CastDto;
import com.mitsudoku.model.movie.MovieCreditsDto;
import com.mitsudoku.model.movie.MovieDto;
import com.mitsudoku.model.movie.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieDbClient movieDbClient;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<MovieDto> getMovie(@RequestParam("query") String query) {
        return movieDbClient.getMovie(query);
    }

    @GetMapping(path = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getMovieId(@RequestParam("query") String query) {
        ResponseDto<MovieDto> movie = movieDbClient.getMovie(query);
        if (movie.getTotalResults() == 0) {
            throw new ItemNotFoundException();
        }
        return movie.getResults().get(0).getId();
    }

    @GetMapping(path = "/credits", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieCreditsDto getMovieCredits(@RequestParam("id") int id) {
        return movieDbClient.getMovieCredits(id);
    }

    @GetMapping(path = "/cast-intersection", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CastDto> getMovieCastIntersection(@RequestParam("first") int first, @RequestParam("second") int second) {
        MovieCreditsDto firstCredits = movieDbClient.getMovieCredits(first);
        MovieCreditsDto secondCredits = movieDbClient.getMovieCredits(second);
        Set<Integer> firstSet = firstCredits.getCast().stream().map(CastDto::getId).collect(Collectors.toSet());
        Set<Integer> intersect = secondCredits.getCast().stream().map(CastDto::getId).filter(firstSet::contains).collect(Collectors.toSet());
        return firstCredits.getCast().stream().filter(i -> intersect.contains(i.getId())).collect(Collectors.toList());
    }

    @GetMapping(value = "/with-cast", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDto getMovieWithCast(@RequestParam("name") String name) {
        ResponseDto<MovieDto> movies = movieDbClient.getMovie(name);
        if (movies.getTotalResults() == 0) {
            throw new ItemNotFoundException();
        }
        MovieDto movie = movies.getResults().get(0);
        movie.setCreditsDto(movieDbClient.getMovieCredits(movie.getId()));
        return movie;
    }

}
