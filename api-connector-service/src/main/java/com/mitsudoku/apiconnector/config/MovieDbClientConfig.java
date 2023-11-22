package com.mitsudoku.apiconnector.config;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import feign.RequestTemplate;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@Slf4j
public class MovieDbClientConfig {

    @Value("${gcloud.project-id}")
    private String projectId;
    private String movieDbSecretToken;

    @Bean
    public RequestInterceptor requestInterceptor() throws IOException {
        movieDbSecretToken = getSecretByName("themoviedb-secret-api");
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.header("Accept", "application/json");
                requestTemplate.header("Authorization", "Bearer " + movieDbSecretToken);
            }
        };
    }


    public String getSecretByName(String name) throws IOException {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
            AccessSecretVersionResponse secret = client.accessSecretVersion(String.format("projects/%s/secrets/%s/versions/latest", projectId, name));
            log.info("Received secret: " + name);
            return secret.getPayload().getData().toStringUtf8();
        }
    }
}
