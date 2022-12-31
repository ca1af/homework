package com.sparta.homework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.homework.dto.SecurityExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(response.getStatus(), "Signed up successfully"));
        response.getWriter().write(json);
    }

//            response.setStatus(HttpStatus.BAD_REQUEST.value());
//        response.setContentType("application/json");
//        try {
//        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//        String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(response.getStatus(), message));
//        response.getWriter().write(json);
//    } catch (Exception exception) {
//        log.error(e.getMessage());
//    }
}
