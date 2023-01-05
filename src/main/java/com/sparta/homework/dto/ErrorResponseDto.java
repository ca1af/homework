package com.sparta.homework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDto {
    int statusCode;
    String message;

    public ErrorResponseDto(String message, int statusCode) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
