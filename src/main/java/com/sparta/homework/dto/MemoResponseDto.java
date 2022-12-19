package com.sparta.homework.dto;

import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemoResponseDto {
    private Long id;
    private String title;
    private String contents;
    private List<Comments> comments;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.comments = memo.getComments();
    }
}
