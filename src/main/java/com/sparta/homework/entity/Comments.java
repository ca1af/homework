package com.sparta.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMO_ID", nullable = false)
    @JsonIgnore
    private Memo memo;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
////    @JsonIgnore
//    private User user;
    @Column
    private String userName;

    public Comments(CommentsRequestDto requestDto, Memo memo, String userName){
        this.comment = requestDto.getComment();
        this.memo = memo;
        this.userName = userName;
    }

    public void update(CommentsRequestDto requestDto){
        this.comment = requestDto.getComment();
    }
// 전부 USER_ID로는 원래 안함. -> 의존관계를 잘 설정해야함.
// 부모와 자식 잘 설정해서, 부모의 Key를 자식이 Join하자.
}
