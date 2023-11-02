package com.mitsudoku.apiconnector.api;


import com.mitsudoku.apiconnector.client.WeatherClient;
import com.mitsudoku.model.weather.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherClient weatherClient;

    @GetMapping
    public WeatherDto getWeather(@RequestParam double latitude,
                                 @RequestParam double longitude,
                                 @RequestParam boolean currentWeather,
                                 @RequestParam String hourly) {
        return weatherClient.getWeather(latitude, longitude, currentWeather, hourly);
    }
}
