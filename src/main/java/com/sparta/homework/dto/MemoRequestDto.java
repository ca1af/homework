package com.sparta.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoRequestDto {
    private String username;
    private String title;
    private String contents;

}
