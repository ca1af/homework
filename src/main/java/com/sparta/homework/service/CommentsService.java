package com.sparta.homework.service;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.CommentsRepository;
import com.sparta.homework.repository.MemoRepository;
import com.sparta.homework.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public CommentsResponseDto createComment(CommentsRequestDto requestDto, Long id, HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);

        if (token != null){
            if (!jwtUtil.validateToken(token)){
                throw new IllegalArgumentException("토큰 에러");
            }

            Memo memo = memoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당하는 메모id가 없다."));

            Comments comment = commentsRepository.saveAndFlush(new Comments(requestDto,memo));
            return new CommentsResponseDto(comment);
        }else {
            return null;
        }
}
}
