package com.sparta.homework.service;

import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.entity.UserRoleEnum;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.MemoRepository;
import com.sparta.homework.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null){
            if (jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰 에러");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );

            Memo memo = memoRepository.saveAndFlush(new Memo(requestDto, user));

            return new MemoResponseDto(memo);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos(HttpServletRequest request) {
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
            System.out.println("role = " + userRoleEnum);

            List<Memo> memos;
            if(userRoleEnum == UserRoleEnum.USER){
                memos = memoRepository.findAllByUserIdOrderByCreatedAtDesc(user.getId());
            } else {
                memos = memoRepository.findAll();
            }
            return memos.stream().map(MemoResponseDto::new).collect(Collectors.toList());
        }
        else {
            return null;
        }
    }

//    @Transactional(readOnly = true)
//    public Memo getCertainMemo(Long id){
//        return memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디 없음"));
//    }

    @Transactional(readOnly = true)
    public MemoResponseDto getCertainMemo(Long id, HttpServletRequest request) {
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
            Memo memo;
            if (userRoleEnum == UserRoleEnum.USER) {
                memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new IllegalArgumentException("해당 메모가 없습니다"));
            } else {
                memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ADMIN - 해당 메모 없음"));
            }
            return new MemoResponseDto(memo);
        } else return null;
    }

    @Transactional
    public String update(Long id, MemoRequestDto requestDto, HttpServletRequest request) {
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

            Memo memo;
            if (userRoleEnum == UserRoleEnum.USER){
                memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                        () -> new NullPointerException("해당 메모는 존재하지 않습니다.")
                );
                memo.update(requestDto);
            } else {
                memo = memoRepository.findById(id).orElseThrow(
                        () -> new NullPointerException("ADMIN - 메모가 존재하지 않습니다.")
                );
                memo.update(requestDto);
            }

        return "수정 완료";
    }else{
            return "수정 실패";
        }
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
            Memo memo;

            if (userRoleEnum == UserRoleEnum.USER){
                memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                        () -> new NullPointerException("해당 메모는 존재하지 않습니다.")
                );
                memoRepository.deleteMemoByUserIdAndId(memo.getId(), user.getId());
            } else {
                memo = memoRepository.findById(id).orElseThrow(
                        () -> new NullPointerException("ADMIN - 메모가 존재하지 않습니다.")
                );
                memoRepository.deleteMemoById(memo.getId());
            }
            return "삭제완료";
        }else{
            return "삭제 실패";
        }
    }
}
