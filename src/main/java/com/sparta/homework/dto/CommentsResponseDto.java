package com.sparta.homework.dto;

import com.sparta.homework.entity.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentsResponseDto {
    Long id;
    String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likes;

    private CommentsResponseDto(Comments comments) {
        this.comment = comments.getComment();
        this.createdAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
        this.likes = comments.getLikes();
        this.id = comments.getId();
    }

    public static CommentsResponseDto from(Comments comments) {
        return new CommentsResponseDto(comments);
    }
}