package com.sparta.homework.dto;

import com.sparta.homework.entity.Comments;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsResponseDto {
    String comment;

    public CommentsResponseDto(Comments comments){
        this.comment = comments.getComment();
    }
}
