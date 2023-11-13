package com.mitsudoku.client;

import com.mitsudoku.config.FeignConfig;
import com.mitsudoku.model.movie.MovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "movie-client", url = "${api-connector-service.url}/api/v1/movie", configuration = FeignConfig.class)
public interface MovieClient {

    @RequestMapping(method = RequestMethod.GET, value = "/with-cast")
    MovieDto getMovieWithCast(@RequestParam("name") String name);

}
