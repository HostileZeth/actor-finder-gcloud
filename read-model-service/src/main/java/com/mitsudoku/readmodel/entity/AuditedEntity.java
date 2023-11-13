package com.mitsudoku.readmodel.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@MappedSuperclass
@RequiredArgsConstructor
public abstract class AuditedEntity {

    private UUID createdUser;

    private Instant createStamp = Instant.now();

    // no need to save changeStamp - graph and events data is immutable
//    private Instant changeStamp;
}
