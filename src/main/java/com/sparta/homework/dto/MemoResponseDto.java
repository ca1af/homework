package com.sparta.homework.dto;

import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MemoResponseDto extends Timestamped {
    private Long id;
    private String userName;
    private String title;
    private String contents;
    private int likes;
    private List<CommentsResponseDto> commentList;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.userName = memo.getUserName();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        if (memo.getLikes().size() == 0){
            this.likes = 0;
        } else {
            this.likes = memo.getLikes().size() -1;
        }
        this.commentList = memo.getComments().stream().map(CommentsResponseDto::from).collect(Collectors.toList());
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }
    public static MemoResponseDto from(Memo memo) {
        return new MemoResponseDto(memo);
    }
}
