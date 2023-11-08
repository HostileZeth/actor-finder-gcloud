package com.mitsudoku.actorfinder.entity;

import com.mitsudoku.model.event.EventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Event extends AuditedEntity {
    @Id
    @SequenceGenerator(name = "event_seq", sequenceName = "EVENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private UUID graphId;
    private Long movieId;
}
