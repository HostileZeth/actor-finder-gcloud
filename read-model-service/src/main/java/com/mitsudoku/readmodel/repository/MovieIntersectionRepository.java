package com.mitsudoku.readmodel.repository;

import com.mitsudoku.readmodel.entity.MovieIntersection;
import com.mitsudoku.readmodel.entity.MovieIntersectionResultDto;
import com.mitsudoku.readmodel.service.AddMovieService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MovieIntersectionRepository extends JpaRepository<MovieIntersection, Long> {

    @Query(nativeQuery = true, value = """
            select ma.movie_id as movieId, ma.actor_id as actorId
            from read_model.movie_actor ma 
            join read_model.movie_actor ma2 on ma.actor_id = ma2.actor_id 
            join read_model.graph_movie gm ON gm.movie_id = ma.movie_id
            where gm.graph_id = ?2 AND ma.movie_id <> ma2.movie_id AND ma2.movie_id = ?1  
            """)
    List<MovieIntersectionResultDto> getMovieIntersections(
            Long movieId,
            UUID graphId);

    @Modifying
    @Query(nativeQuery = true, 
            value = """
                    insert into read_model.movie_intersection (movie_id, movie2_id, actor_id, graph_id)
                            values (?1, ?2, ?3, ?4)""")
    void putMovieIntersections(Long movieId, Long movie2id, Long actorId, UUID graphId);

}
