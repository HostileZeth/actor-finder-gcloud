package com.mitsudoku.actorfinder.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actor_graph")
@RequiredArgsConstructor
public class ActorGraphController {

    @PostMapping
    public Long createGraph() {
        return 1L; // TODO
    }

    @GetMapping
    public void getGraph(@RequestParam(name = "id") Long id) {
        // TODO
    }

}
