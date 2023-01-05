package com.sparta.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class LikesMemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "MEMO_ID", nullable = false)
//    private Memo memo;
    private Long memoId;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public LikesMemo(Memo memo, User user) {
        this.memoId = memo.getId();
        this.user = user;
    }

}
