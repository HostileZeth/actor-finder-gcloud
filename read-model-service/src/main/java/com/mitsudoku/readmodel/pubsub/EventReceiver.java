package com.mitsudoku.readmodel.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.mitsudoku.event.EventDto;
import com.mitsudoku.event.EventType;
import com.mitsudoku.readmodel.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventReceiver {

    @Value("${pubsub.project-id}")
    private String projectId;

    @Value("${pubsub.sub-id}")
    private String subId;

    private final ObjectMapper objectMapper;

    private final Map<EventType, EventService> serviceMap;

    @PostConstruct
    public void subscribe() {
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(projectId, subId);
        Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, this::processMessage).build();
        subscriber.startAsync().awaitRunning();
    }

    private void processMessage(PubsubMessage message, AckReplyConsumer consumer) {
        System.out.println("Id: " + message.getMessageId());
        try {
            EventDto eventDto = objectMapper.readValue(message.getData().toStringUtf8(), EventDto.class);
            serviceMap.get(eventDto.getEventType()).process(eventDto);
            consumer.ack();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
