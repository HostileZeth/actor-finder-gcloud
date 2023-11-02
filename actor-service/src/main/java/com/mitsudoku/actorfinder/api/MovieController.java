package com.mitsudoku.actorfinder.api;

import com.mitsudoku.actorfinder.service.CastCompareService;
import com.mitsudoku.client.MovieClient;
import com.mitsudoku.model.movie.CastDto;
import com.mitsudoku.model.movie.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieClient movieClient;
    private final CastCompareService castCompareService;

    @GetMapping("/cast-intersection")
    public List<CastDto> getCastIntersection(String movie1, String movie2) {
        MovieDto movieDto1 = movieClient.getMovieWithCast(movie1);
        MovieDto movieDto2 = movieClient.getMovieWithCast(movie2);
        List<CastDto> result = castCompareService.getCastIntersection(movieDto1, movieDto2);
        return result;
    }

}
