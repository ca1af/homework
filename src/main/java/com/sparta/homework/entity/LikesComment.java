package com.sparta.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class LikesComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "COMMENTS_ID", nullable = false)
//    private Comments comments;
    private Long commentsId;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public LikesComment(Comments comments, User user) {
        this.commentsId = comments.getId();
        this.user = user;
    }
}