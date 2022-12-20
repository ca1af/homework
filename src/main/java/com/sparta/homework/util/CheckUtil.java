package com.sparta.homework.util;

import com.sparta.homework.entity.User;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Component
public class CheckUtil {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public User tokenChecker(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;


        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰 에러입니다");
            }
            return userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자가 존재하지 않습니다")
            );
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰 에러입니다");
    }
}
