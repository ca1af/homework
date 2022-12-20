package com.sparta.homework.service;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.entity.UserRoleEnum;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.CommentsRepository;
import com.sparta.homework.repository.MemoRepository;
import com.sparta.homework.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public CommentsResponseDto createComment(CommentsRequestDto requestDto, Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 메모id가 없다."));

            Comments comment = commentsRepository.saveAndFlush(new Comments(requestDto, memo, user.getUsername()));

            return new CommentsResponseDto(comment);
        } else {
            return null;
        }
    }

    @Transactional
    public String updateComment(CommentsRequestDto requestDto, Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            UserRoleEnum userRoleEnum = user.getRole();

            Comments comment;
            if (userRoleEnum == UserRoleEnum.USER) {
                comment = commentsRepository.findByIdAndUserName(id, user.getUsername()).orElseThrow(
                        () -> new NullPointerException("해당 댓글은 존재하지 않습니다.")
                );
                comment.update(requestDto);
            } else {
                comment = commentsRepository.findById(id).orElseThrow(
                        () -> new NullPointerException("ADMIN - 댓글이 존재하지 않습니다.")
                );
                comment.update(requestDto);
            }
            return "수정 완료";
        }
        return "수정 실패";
    }

    @Transactional
    public String deleteMemo(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            UserRoleEnum userRoleEnum = user.getRole();

            if (userRoleEnum == UserRoleEnum.USER) {
                commentsRepository.findByIdAndUserName(id, user.getUsername()).orElseThrow(
                        () -> new NullPointerException("해당 댓글은 존재하지 않습니다.")
                );
                commentsRepository.deleteCommentsByIdAndUserName(id, user.getUsername());
            } else {
                commentsRepository.findById(id).orElseThrow(
                        () -> new NullPointerException("ADMIN - 댓글이 존재하지 않습니다.")
                );
                commentsRepository.deleteCommentsById(id);
            }
            return "삭제 완료";
        }
        return "삭제 실패";

    }
}
