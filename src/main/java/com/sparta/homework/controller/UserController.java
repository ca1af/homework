package com.sparta.homework.controller;

import com.sparta.homework.dto.LoginRequestDto;
import com.sparta.homework.dto.SignupRequestDto;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.security.UserDetailsImpl;
import com.sparta.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        if (signupRequestDto.isAdmin()) {
            return "ADMIN";
        } else {
            return "Ok";
        }
    }

    @PostMapping("/login")
    public String login(final @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String generatedToken = userService.login(loginRequestDto);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);
        return "success";
    }

    @PostMapping("/login/success")
    public String loginSuccess(final @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String generatedToken = userService.login(loginRequestDto);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);
        return "success";
    }
}