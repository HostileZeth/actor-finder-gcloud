package com.mitsudoku.readmodel.api;

import com.mitsudoku.readmodel.entity.ActorGraph;
import com.mitsudoku.readmodel.mapping.ActorGraphMapper;
import com.mitsudoku.readmodel.model.ActorGraphDto;
import com.mitsudoku.readmodel.repository.ActorGraphRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/actor-graph")
public class ActorGraphController {

    private final ActorGraphRepository actorGraphRepository;
    private final ActorGraphMapper actorGraphMapper;

    @GetMapping("/{id}")
    public ActorGraphDto getActorGraph(@PathVariable UUID id) {
        return actorGraphRepository.findById(id).map(actorGraphMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }
}
