package com.mitsudoku.actorfinder.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
// TODO create sql script / changelog
public class Actor {
    @Id
    private Long id;

}