package com.sparta.homework.dto;

import com.sparta.homework.entity.Likes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikesResponseDto {
    Long count;

    private LikesResponseDto(Likes likes){
        this.count = likes.getCount();
    }

    public static LikesResponseDto from(Likes likes){
        return new LikesResponseDto(likes);
    }
}