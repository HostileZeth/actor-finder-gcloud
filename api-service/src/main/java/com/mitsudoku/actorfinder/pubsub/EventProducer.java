package com.mitsudoku.actorfinder.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.mitsudoku.event.EventDto;
import lombok.RequiredArgsConstructor;
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
public class EventProducer {

    @Value("${pubsub.event-topic}")
    private String eventTopic;

    @Value("${pubsub.project-id}")
    private String projectId;

    private Publisher publisher;

    private final ObjectMapper objectMapper;


    @PostConstruct
    public void initializePublisher() throws IOException {
        TopicName topicName = TopicName.of(projectId, eventTopic);
        publisher = Publisher.newBuilder(topicName).setEnableMessageOrdering(true).build();
    }

    // TODO test this
    public void sendEvent(EventDto event) {
        try {
            if (Objects.isNull(publisher)) {
                log.warn("Failed to send event due to publisher not initialized");
                return;
            }
            ApiFuture<String> publish = publisher.publish(
                    PubsubMessage.newBuilder()
                            .setData(ByteString.copyFromUtf8(objectMapper.writeValueAsString(event)))
                            .setOrderingKey(event.getGraphId().toString())
                            .build());
            log.info("Published event message with id = " + publish.get());
        } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
            log.error("Failed to send event with message " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
