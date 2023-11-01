package com.mitsudoku.apiconnector.client;

import com.mitsudoku.apiconnector.config.MovieDbClientConfig;
import com.mitsudoku.apiconnector.model.MovieCreditsDto;
import com.mitsudoku.apiconnector.model.MovieDto;
import com.mitsudoku.apiconnector.model.ResponseDto;
import com.mitsudoku.config.FeignConfig;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// TODO
@FeignClient(name = "movie-client", url = "${movie.api.url}", configuration = {FeignConfig.class, MovieDbClientConfig.class})
public interface MovieDbClient {

    @RequestMapping(method = RequestMethod.GET, value = "/search/movie")
    @Headers({"accept: application/json", "Authorization: Bearer ${movie.api.token}"})
    ResponseDto<MovieDto> getMovie(@RequestParam("query") String query);

    @RequestMapping(method = RequestMethod.GET, value = "movie/{movie-id}/credits")
    @Headers({"accept: application/json", "Authorization: Bearer ${movie.api.token}"})
    MovieCreditsDto getMovieCredits(@PathVariable("movie-id") int id);

}
