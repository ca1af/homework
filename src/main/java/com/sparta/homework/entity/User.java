package com.sparta.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-zA-z0-9]+")
    @Column(nullable = false, unique = true)
    private String username;


    @Size(min = 8, max = 15)
    @Pattern(regexp = "[a-zA-z0-9]+")
    @Column(nullable = false)
    private String password;

    @OneToMany
    List<Memo> memos = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
