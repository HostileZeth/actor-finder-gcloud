package com.mitsudoku.actorfinder.api;

import com.mitsudoku.actorfinder.entity.ActorGraph;
import com.mitsudoku.actorfinder.repository.ActorGraphRepository;
import com.mitsudoku.actorfinder.repository.EventRepository;
import com.mitsudoku.actorfinder.service.AuthUserService;
import com.mitsudoku.actorfinder.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/actor-graph")
@RequiredArgsConstructor
public class ActorGraphController {
    private final ActorGraphRepository graphRepository;
    private final EventRepository eventRepository;
    private final EventService eventService;
    private final AuthUserService authUserService;

    @PostMapping
    @Transactional
    public UUID createGraph() {
        UUID userId = authUserService.getUserId();
        ActorGraph actorGraph = new ActorGraph();
        actorGraph.setCreateStamp(Instant.now());
        actorGraph.setCreatedUser(userId);
        actorGraph = graphRepository.saveAndFlush(actorGraph);
        eventService.processGraphEvent(actorGraph, userId);
        return actorGraph.getId();
    }

    @GetMapping
    public ActorGraph getGraph(@RequestParam(name = "id") UUID id) {
        return graphRepository.getReferenceById(id);
    }

}
