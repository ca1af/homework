package com.sparta.homework.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.homework.dto.ErrorResponseDto;
import com.sparta.homework.dto.SecurityExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

@Slf4j // 로그 찍어주는 애.
@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto illegalException(IllegalArgumentException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        return new ErrorResponseDto(e.getMessage(), response.getStatus());
    }
    // ResponseEntity <- status 코드를 기본 가지고있다. + 여러기능 가지고잇음. dto 굳이 만들 필요가 없었다.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto validationException(MethodArgumentNotValidException e, HttpServletResponse response) {
        // 이부분은 @Valid 에서 실패시 수행된다.

        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        return new ErrorResponseDto(message, response.getStatus());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto UsernameNotFoundException(UsernameNotFoundException e, HttpServletResponse response) {
        // 이부분은 UserDetailsServiceImpl 에서 던지는 에러이다.
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        return new ErrorResponseDto(e.getMessage(), response.getStatus());
    }
}
