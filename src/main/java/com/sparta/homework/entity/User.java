package com.sparta.homework.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
@Getter
public class User {
    @Id
    private Long id;
}
