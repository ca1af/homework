package com.sparta.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "MEMO_ID", nullable = false)
    private Memo memo;
    @ManyToOne
    @JoinColumn(name = "COMMENTS_ID", nullable = false)
    private Comments comments;
    @Column(nullable = false)
    private Long count = 0L;
}
