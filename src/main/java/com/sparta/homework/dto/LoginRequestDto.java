package com.sparta.homework.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class LoginRequestDto {
    private String username;

    private String password;
}