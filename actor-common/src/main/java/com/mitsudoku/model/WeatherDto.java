package com.mitsudoku.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherDto {
    private String timezone;

    private CurrentWeather currentWeather;

    @JsonProperty("currentWeather")
    @JsonAlias("current_weather")
    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    @Data
    @NoArgsConstructor
    public static class CurrentWeather {
        private Double temperature;
    }
}
