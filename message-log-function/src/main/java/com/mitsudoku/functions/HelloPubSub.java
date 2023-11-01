package com.mitsudoku.functions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.functions.CloudEventsFunction;
import com.mitsudoku.function.common.db.DbConnection;
import io.cloudevents.CloudEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

/**
 * Hello world!
 */
@Slf4j
public class HelloPubSub implements CloudEventsFunction {

    private DataSource connectionPool;

    // Simple function to return "Hello World"
    @Override
    public void accept(CloudEvent event)
            throws SQLException {
        try {
            if (event.getData() == null) {
                writeDb(new RequestLog("Empty payload", LocalDateTime.now()));
            }
            String dataFromJson = getDataFromJson(new String(event.getData().toBytes(), StandardCharsets.UTF_8));
            writeDb(new RequestLog(dataFromJson, LocalDateTime.now()));
        } catch (JsonProcessingException e) {
            writeDb(new RequestLog("Failed to process JSON", LocalDateTime.now()));
        }
    }

    private void writeDb(RequestLog requestLog) throws SQLException {
        if (connectionPool == null) {
            connectionPool = DbConnection.createConnectionPool();
        }
        Connection connection = connectionPool.getConnection();

        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO request_log (request_address, time)" +
                " VALUES ('%s', '%s')", requestLog.getRequestAddress(), requestLog.getTime()));
        connection.close();
    }

    // decode data from BASE64
    private String getDataFromJson(String input) throws JsonProcessingException {
        log.info("Received input: " + input);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> tr = new TypeReference<Map<String, Object>>() {
        };
        Map<String, Object> stringObjectMap = objectMapper.readValue(input, tr);
        Map<String, Object> message = (Map<String, Object>) stringObjectMap.get("message");
        String data = (String) message.get("data");
        return new String(Base64.getDecoder().decode(data));
    }

    @Data
    static class RequestLog {
        private final String requestAddress;
        private final LocalDateTime time;
    }
}
