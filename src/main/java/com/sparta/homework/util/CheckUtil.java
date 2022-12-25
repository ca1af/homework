package com.sparta.homework.util;

import com.sparta.homework.dto.UtilDto;
import com.sparta.homework.entity.User;
import com.sparta.homework.entity.UserRoleEnum;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CheckUtil {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public UtilDto tokenChecker(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰 에러입니다");
            }
           User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new IllegalArgumentException("유저없다"));
//            UserRoleEnum userRoleEnum = user.getRole();
//            String auth1 = claims.get("auth", String.class);
            userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자가 존재하지 않습니다")
            );
            return UtilDto.from(user);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰 에러입니다");
    }
}

