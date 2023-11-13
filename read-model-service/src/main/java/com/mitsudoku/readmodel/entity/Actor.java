package com.mitsudoku.readmodel.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Actor {
    @Id
    private Long id; // fill with api output id

    private boolean adult;
    private int gender;
    private String knownForDepartment;
    private String name;
    private String originalName;
    private double popularity;
    private String profilePath;
    
}