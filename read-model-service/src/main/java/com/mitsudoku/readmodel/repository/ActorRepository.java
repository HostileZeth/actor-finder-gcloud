package com.mitsudoku.readmodel.repository;

import com.mitsudoku.readmodel.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
