package com.mitsudoku.actorfinder.repository;

import com.mitsudoku.actorfinder.entity.ActorGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorGraphRepository extends JpaRepository<ActorGraph, Long> {
}
