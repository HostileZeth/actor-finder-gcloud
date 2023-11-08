package com.mitsudoku.actorfinder.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ActorGraph extends AuditedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

}
