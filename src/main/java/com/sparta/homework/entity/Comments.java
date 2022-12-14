package com.sparta.homework.entity;

import com.sparta.homework.dto.CommentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comments extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userName;
    @Column
    private String comment;
    private int likes;
    public void setLikesCountComment(int likes) {
        this.likes = likes;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMO_ID", nullable = false)
    // JsonIgnore는 comments에서 memo를 무시하라는 어노테이션. (Json 만들 때)
    private Memo memo;
    //comments -> memo -> getComments -> emmo...~ 오류 200줄

    public Comments(CommentsRequestDto requestDto, Memo memo, String userName){
        this.comment = requestDto.getComment();
        this.memo = memo;
        this.userName = userName;
    }
    public void update(CommentsRequestDto requestDto){
        this.comment = requestDto.getComment();
    }
}