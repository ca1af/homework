package com.sparta.homework.dto;

import com.sparta.homework.entity.Comments;
import com.sparta.homework.repository.LikesCommentRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentsResponseDto {
    String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likes;

    private CommentsResponseDto(Comments comments) {
        this.comment = comments.getComment();
        this.createdAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
//        if (comments.getLikes().size() == 0){
//            this.likes = 0;
//        } else {
//            this.likes = comments.getLikes().size() -1;
//        }
        this.likes = comments.getLikes();
    }

    public static CommentsResponseDto from(Comments comments) {
        return new CommentsResponseDto(comments);
    }
}