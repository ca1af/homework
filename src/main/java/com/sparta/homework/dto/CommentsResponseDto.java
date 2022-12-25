package com.sparta.homework.dto;

import com.sparta.homework.entity.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentsResponseDto {
    String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private CommentsResponseDto(Comments comments){
        this.comment = comments.getComment();
        this.createdAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
    }
    public static CommentsResponseDto from(Comments comments){
        return new CommentsResponseDto(comments);
    }
}