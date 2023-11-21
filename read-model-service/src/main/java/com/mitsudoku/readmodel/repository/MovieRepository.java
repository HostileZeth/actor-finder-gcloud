package com.mitsudoku.readmodel.repository;

import com.mitsudoku.readmodel.entity.ActorGraph;
import com.mitsudoku.readmodel.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    /* get similarities by graph_id and movie_id
        select * from movie_actor ma
        where ma.movie_id = 335984
        and ma.actor_id in (
        select ma2.actor_id  from movie_actor ma2
        where movie_id in (select gm.movie_id from graph_movie gm where gm.graph_id = '7864928f-d35c-411f-b1b8-d64b58ad2c30')
        and ma2.movie_id <> 335984
        )
    */

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM read_model.graph_movie gm WHERE gm.movie_id = :movieId")
    Long countGraphs(Long movieId);

}
