package com.mitsudoku.readmodel.repository;

import com.mitsudoku.readmodel.entity.MovieIntersection;
import com.mitsudoku.readmodel.service.AddMovieService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MovieIntersectionRepository extends JpaRepository<MovieIntersection, Long> {

    /* get similarities by graph_id and movie_id

        select * from movie_actor ma
        where ma.movie_id = 335984
        and ma.actor_id in (
        select ma2.actor_id  from movie_actor ma2
        where movie_id in (select gm.movie_id from graph_movie gm where gm.graph_id = '7864928f-d35c-411f-b1b8-d64b58ad2c30')
        and ma2.movie_id <> 335984
        )
    */
    @Query(nativeQuery = true, value = "select ma.movie_id as movieId, ma.actor_id as actorId from read_model.movie_actor ma\n" +
            "        where ma.movie_id = ?1\n" +
            "        and ma.actor_id in (\n" +
            "        select ma2.actor_id  from read_model.movie_actor ma2\n" +
            "        where movie_id in (select gm.movie_id from read_model.graph_movie gm where gm.graph_id = ?2)\n" +
            "        and ma2.movie_id <> ?1\n" +
            "        )")
    List<AddMovieService.MovieActorIntersectionDto> getMovieIntersections(Long movieId, UUID graphId);
    // TODO fix dto mapping

    /* insert movie intersections

        insert into movie_intersection (movie_id, movie2_id, actor_id, graph_id)
        values (?1, ?2, ?3, ?4)
    */

}
