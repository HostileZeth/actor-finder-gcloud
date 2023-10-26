package com.example.apiconnectorservice.client;

import com.mitsudoku.model.WeatherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "weather-client", url = "${weather.api.url}")
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
