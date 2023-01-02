package com.sparta.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "MEMO_ID", nullable = false)
//    private Memo memo;
    @ManyToOne
    @JoinColumn(name = "COMMENTS_ID", nullable = false)
    private Comments comments;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Likes(Comments comments, User user) {
        this.comments = comments;
        this.user = user;
    }
//    public Likes(Memo memo){
//        this.memo = memo;
//    }
}