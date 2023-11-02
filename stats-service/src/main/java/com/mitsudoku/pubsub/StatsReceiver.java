package com.mitsudoku.pubsub;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.mitsudoku.model.weather.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class StatsReceiver {

    @Value("${pubsub.project-id}")
    private String projectId;

    @Value("${pubsub.sub-id}")
    private String subId;

    private Map<RequestType, Long> stats = new HashMap<>();

    @PostConstruct
    public void subscribe() {
        for (RequestType value : RequestType.values()) {
            stats.put(value, 0L);
        }
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(projectId, subId);
        Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, this::processMessage).build();
        subscriber.startAsync().awaitRunning();
    }

    private void processMessage(PubsubMessage message, AckReplyConsumer consumer) {
        System.out.println("Id: " + message.getMessageId());
        String type = message.getData().toStringUtf8();
        RequestType requestType = RequestType.valueOf(type);
        stats.put(requestType, stats.get(requestType) + 1);
        log.info(type + " = " + stats.get(requestType));
        consumer.ack();
    }
}
