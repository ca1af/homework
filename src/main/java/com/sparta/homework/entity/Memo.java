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

    @OneToMany(mappedBy = "memo")
    List<Comments> comments = new ArrayList<>();

    public Memo(String title, String contents, User user) {
        this.contents = contents;
        this.title = title;
        this.user = user;
    }

    public Memo(MemoRequestDto requestDto, User user) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.user = user;
        //이부분에서 userId만 해서 해도 되나...foreign 키로 userId가 들어가있지않나?
        // 아니 근데 JoinColumn 할려면...user 객체로 받아와야 하는 것이 아닌가?
    }

    public void update(MemoRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }
}
