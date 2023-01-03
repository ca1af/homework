package com.sparta.homework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponseDto {
    int statusCode;
    String message;

    public ErrorResponseDto(String message, int statusCode) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public void illegalException(IllegalArgumentException e, HttpServletResponse response) {
//        response.setStatus(HttpStatus.BAD_REQUEST.value());
//        response.setContentType("application/json");
//        try {
//            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(response.getStatus(), "Typing Error"));
//            response.getWriter().write(json);
//        } catch (Exception exception) {
//            log.error(e.getMessage());
//        }
//    }