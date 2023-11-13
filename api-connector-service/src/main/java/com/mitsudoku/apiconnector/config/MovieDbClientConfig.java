package com.mitsudoku.apiconnector.config;

import feign.RequestTemplate;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MovieDbClientConfig {

    @Value("${movie.api.token}")
    private String bearer;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                requestTemplate.header("Accept", "application/json");
                requestTemplate.header("Authorization", "Bearer " + bearer);
            }
        };
    }

//    @Bean
//    public LoadBalancerFeignRequestTransformer transformer() {
//        return (request, instance) -> {
//            Map<String, Collection<String>> headers = new HashMap<>(request.headers());
//            headers.put("accept", Collections.singletonList("application/json"));
//            headers.put("Authorization", Collections.singletonList(bearer));
//            return Request.create(request.httpMethod(), request.url(), headers, request.body(), request.charset(),
//                    request.requestTemplate());
//        };
//    }
}
