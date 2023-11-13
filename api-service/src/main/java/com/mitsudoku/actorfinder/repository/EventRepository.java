package com.mitsudoku.actorfinder.repository;

import com.mitsudoku.actorfinder.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = """
            SELECT count(*) > 0
            FROM Event e
            where e.eventType = 'ADD_MOVIE' and e.movieId = :movieId and e.graphId = :graphId
            and e.createStamp > coalesce((
            select MAX(e2.createStamp)
            FROM Event e2
            where e2.eventType = 'REMOVE_MOVIE' and e2.movieId = :movieId and e2.graphId = :graphId
            ), to_timestamp('0'))""")
    boolean isMovieAlreadyAdded(UUID graphId, Long movieId);

}
