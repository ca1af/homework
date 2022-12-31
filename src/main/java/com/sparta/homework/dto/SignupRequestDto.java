package com.sparta.homework.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {
    @Size(min = 4, max = 10, message = "input 4-10 characters")
    @Pattern(regexp = "[a-zA-z0-9]+", message = "Special characters(including spaces) are not allowed.")
    private String username;
    @Size(min = 8, max = 15, message = "input 8-15 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9ㄱ-힣]).+$", message = "Spaces are not allowed")
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}