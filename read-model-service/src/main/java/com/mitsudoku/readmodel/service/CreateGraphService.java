package com.mitsudoku.readmodel.service;

import com.mitsudoku.readmodel.entity.ActorGraph;
import com.mitsudoku.event.EventDto;
import com.mitsudoku.event.EventType;
import com.mitsudoku.readmodel.repository.ActorGraphRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CreateGraphService implements EventService {

    private final ActorGraphRepository actorGraphRepository;

    @Override
    public void process(EventDto eventDto) {
        log.info("CREATE_GRAPH event received");
        ActorGraph actorGraph = new ActorGraph();
        actorGraph.setId(eventDto.getGraphId());
        actorGraph.setCreateStamp(eventDto.getCreateStamp());
        actorGraph.setCreatedUser(eventDto.getCreatedUser());
        actorGraphRepository.save(actorGraph);
    }

    @Override
    public EventType eventType() {
        return EventType.CREATE_GRAPH;
    }
}
