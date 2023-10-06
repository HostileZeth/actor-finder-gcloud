package com.mitsudoku.actorfinder.pubsub;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.mitsudoku.model.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatsProducer {

    @Value("${pubsub.stats-topic}")
    private String statsTopic;

    @Value("${pubsub.project-id}")
    private String projectId;

    private Publisher publisher;

    @PostConstruct
    public void initializePublisher() throws IOException {
        TopicName topicName = TopicName.of(projectId, statsTopic);
        publisher = Publisher.newBuilder(topicName).build();
    }

    @SneakyThrows
    public void sendStats(RequestType type) {
        if (Objects.isNull(publisher)) {
            log.warn("Failed to send statistics due to publisher not initialized");
            return;
        }
        ApiFuture<String> publish = publisher.publish(PubsubMessage.newBuilder().setData(ByteString.copyFromUtf8(type.toString())).build());
        log.info("Published stat message with id = " + publish.get());
    }

}
