package com.sparta.homework.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.homework.dto.SecurityExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

@Slf4j // 로그 찍어주는 애.
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void illegalException(IllegalArgumentException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(response.getStatus(), "Typing Error"));
            response.getWriter().write(json);
        } catch (Exception exception) {
            log.error(e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void validationException(MethodArgumentNotValidException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        try {
            String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(response.getStatus(), message));
            response.getWriter().write(json);
        } catch (Exception exception) {
            log.error(e.getMessage());
        }
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public void authExecption(AccessDeniedException e, HttpServletResponse response){
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpStatus.FORBIDDEN.value());
//        log.warn("에러입니다");
//    }
}
