package com.sparta.homework.dto;

import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemoResponseDto extends Timestamped {
    private Long id;
    private String userName;
    private String title;
    private String contents;
    private List<Comments> comments;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.userName = memo.getUserName();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.comments = memo.getComments();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }
}
