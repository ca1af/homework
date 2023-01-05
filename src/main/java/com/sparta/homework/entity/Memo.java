package com.sparta.homework.entity;

import com.sparta.homework.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @Column
    private String userName;
    @OrderBy(value = "createdAt DESC")
    @OneToMany(mappedBy = "memo", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<Comments> comments = new ArrayList<>();

    private int likes;

    public void setLikesCount(int likes) {
        this.likes = likes;
    }

    public Memo(String title, String contents, User user, String userName) {
        this.contents = contents;
        this.title = title;
        this.user = user;
        this.userName = userName;
    }

    public Memo(MemoRequestDto requestDto, User user) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.userName = user.getUsername();
        this.user = user;
    }

    public void update(MemoRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }
}
