package com.mitsudoku.functions;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.mitsudoku.db.DbConnection;
import lombok.Data;

import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 * Hello world!
 *
 */
public class HelloWorld implements HttpFunction
{

    // Simple function to return "Hello World"
    @Override
    public void service(HttpRequest request, HttpResponse response)
            throws IOException, SQLException {
        BufferedWriter writer = response.getWriter();
        writeDb(new RequestLog(request.getPath(), LocalDateTime.now()));
        writer.write("Hello World!");
        writer.close();
    }

    private void writeDb(RequestLog requestLog) throws SQLException {
        DataSource connectionPool = DbConnection.createConnectionPool();
        Connection connection = connectionPool.getConnection();

        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO request_log (request_address, time)" +
                " VALUES ('%s', '%s')", requestLog.getRequestAddress(), requestLog.getTime()));
        connection.close();
    }

    @Data
    static class RequestLog {
        private final String requestAddress;
        private final LocalDateTime time;
    }
}
