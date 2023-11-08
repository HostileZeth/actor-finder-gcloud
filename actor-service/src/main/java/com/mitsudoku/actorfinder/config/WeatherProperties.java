package com.mitsudoku.actorfinder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("weather.city")
public class WeatherProperties {
    private String name;
    private Double lat;
    private Double lng;
    private boolean currentWeather;
    private String hourly;
}
