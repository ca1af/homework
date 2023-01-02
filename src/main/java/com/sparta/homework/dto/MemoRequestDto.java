package com.sparta.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemoRequestDto {
    private String username;
    private String title;
    private String contents;

}
