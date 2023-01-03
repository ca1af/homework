package com.sparta.homework.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.homework.dto.SecurityExceptionDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(response.getStatus(), "Login failed"));
        response.getWriter().write(json);
    }
}
