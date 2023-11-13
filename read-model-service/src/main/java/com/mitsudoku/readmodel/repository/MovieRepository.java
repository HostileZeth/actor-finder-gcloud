package com.mitsudoku.readmodel.repository;

import com.mitsudoku.readmodel.entity.ActorGraph;
import com.mitsudoku.readmodel.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
