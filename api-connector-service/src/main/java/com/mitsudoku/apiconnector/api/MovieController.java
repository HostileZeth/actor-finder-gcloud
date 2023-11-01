package com.mitsudoku.apiconnector.api;

import com.google.api.client.http.HttpMediaType;
import com.mitsudoku.apiconnector.client.MovieDbClient;
import com.mitsudoku.apiconnector.exception.ItemNotFoundException;
import com.mitsudoku.apiconnector.model.MovieCreditsDto;
import com.mitsudoku.apiconnector.model.MovieDto;
import com.mitsudoku.apiconnector.model.ResponseDto;
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
        if (movie.total_results == 0) {
            throw new ItemNotFoundException();
        }
        return movie.results.get(0).getId();
    }

    @GetMapping(path = "/credits", produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieCreditsDto getMovieCredits(@RequestParam("id") int id) {
        return movieDbClient.getMovieCredits(id);
    }

    @GetMapping(path = "/cast-intersection", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getMovieCredits(@RequestParam("first") int first, @RequestParam("second") int second) {
        MovieCreditsDto firstCredits = movieDbClient.getMovieCredits(first);
        MovieCreditsDto secondCredits = movieDbClient.getMovieCredits(second);

        Set<Integer> firstSet = firstCredits.getCast().stream().map(MovieCreditsDto.Cast::getId).collect(Collectors.toSet());
        return secondCredits.getCast().stream().map(MovieCreditsDto.Cast::getId).filter(firstSet::contains).collect(Collectors.toList());
    }

}
