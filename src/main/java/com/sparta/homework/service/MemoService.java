package com.sparta.homework.service;

import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.dto.MessageDto;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.jwt.JwtUtil;
import com.sparta.homework.repository.MemoRepository;
import com.sparta.homework.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public List<Memo> getMemos(HttpServletRequest request) {
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

            return memoRepository.findAllByUserIdOrderByCreatedAtDesc(user.getId());
        }
        else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Memo getCertainMemo(Long id){
        return memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디 없음"));
    }



    @Transactional
    public Long update(Long id, MemoRequestDto requestDto, HttpServletRequest request) {
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

            Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 메모는 존재하지 않습니다.")
            );

            memo.update(requestDto);

        return memo.getId();
    }else{
            return null;
        }
    }


    @Transactional
    public MessageDto deleteMemo(Long id, HttpServletRequest request) {
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

            Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 메모는 존재하지 않습니다.")
            );

            memoRepository.deleteMemoByUserIdAndId(memo.getId(), user.getId());

            MessageDto messageDto = new MessageDto();
            messageDto.setMessage("삭제가 완료되었습니다.");

            return messageDto;
        }else{
            return null;
        }

    }

}
