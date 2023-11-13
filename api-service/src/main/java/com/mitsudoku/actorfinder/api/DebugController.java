package com.mitsudoku.actorfinder.api;

import com.mitsudoku.actorfinder.config.CloudProperties;
import com.mitsudoku.actorfinder.config.WeatherProperties;
import com.mitsudoku.actorfinder.pubsub.StatsProducer;
import com.mitsudoku.client.WeatherClient;
import com.mitsudoku.model.weather.RequestType;
import com.mitsudoku.model.weather.WeatherDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/debug")
@RequiredArgsConstructor
public class DebugController {

    private final CloudProperties config;
    private final WeatherProperties weatherProperties;
    private final WeatherClient weatherClient;
    private final StatsProducer statsProducer;

    @GetMapping("/test")
    public String test() {
        statsProducer.sendStats(RequestType.TEST);
        WeatherDto weather = weatherClient.getWeather(weatherProperties.getLat(), weatherProperties.getLng(), weatherProperties.isCurrentWeather(), weatherProperties.getHourly());
        String format = "There will be an movie's actor list analyzer. And a random number for today is %d\n";
        String response = String.format(format, config.getRandomNumber());
        if (weather.getCurrentWeather() != null) {
            response += String.format("And the weather in %s is %.2f", weatherProperties.getName(), weather.getCurrentWeather().getTemperature());
        } else {
            response += "And the weather is unknown!";
        }
        return response;
    }

    @GetMapping("/db")
    public List<Database> db() {
        List<Database> query = this.template.query("SELECT * FROM pg_catalog.pg_database", rowMapper);
        statsProducer.sendStats(RequestType.DB);
        return query;
    }


    private final Log log = LogFactory.getLog(getClass());

    private final JdbcTemplate template;

    private final RowMapper<Database> rowMapper = (rs, rownum) -> new Database(rs.getLong("oid"), rs.getString("datname"));

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Database {
        private Long oid;
        private String datname;
    }
}
