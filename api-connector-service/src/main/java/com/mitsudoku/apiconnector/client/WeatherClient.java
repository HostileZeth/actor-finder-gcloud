package com.mitsudoku.apiconnector.client;

import com.mitsudoku.config.FeignConfig;
import com.mitsudoku.model.weather.WeatherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * Weather client for debug purposes
 */
@FeignClient(name = "weather-client", url = "${weather.api.url}", configuration = FeignConfig.class)
public interface WeatherClient {

    @RequestMapping(method = RequestMethod.GET, value = "?latitude={latitude}&longitude={longitude}&current_weather={currentWeather}&hourly={hourly}")
    WeatherDto getWeather(@PathVariable("latitude") double latitude,
                          @PathVariable("longitude") double longitude,
                          @PathVariable("currentWeather") boolean currentWeather,
                          @PathVariable("hourly") String hourly);

    @RequestMapping(method = RequestMethod.GET, value = "?latitude={latitude}&longitude={longitude}&currentWeather={currentWeather}&hourly={hourly}")
    String getWeatherAsJson(@PathVariable("latitude") double latitude,
                            @PathVariable("longitude") double longitude,
                            @PathVariable("currentWeather") boolean currentWeather,
                            @PathVariable("hourly") String hourly);
}
