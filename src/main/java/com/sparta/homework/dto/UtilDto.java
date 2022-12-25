package com.sparta.homework.dto;

import com.sparta.homework.entity.User;
import com.sparta.homework.entity.UserRoleEnum;
import lombok.Getter;

import java.util.Optional;

@Getter
public class UtilDto {
    String username;
    UserRoleEnum userRoleEnum;
    private UtilDto(User user){
        this.username = user.getUsername();
        this.userRoleEnum = user.getRole();
    }
    public static UtilDto from(User user){
        return new UtilDto(user);
    }

}
