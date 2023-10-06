package com.mitsudoku.actorfinder.api;

import com.mitsudoku.actorfinder.config.CloudConfig;
import com.mitsudoku.actorfinder.pubsub.StatsProducer;
import com.mitsudoku.model.RequestType;
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
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorAnalyzeController {

    private final CloudConfig config;
    private final StatsProducer statsProducer;

    @GetMapping("/test")
    public String test() {
        statsProducer.sendStats(RequestType.TEST);
        return String.format("There will be an movie's actor list analyzer. And a random number for today is %d", config.getRandomNumber());
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
