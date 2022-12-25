package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.dto.UtilDto;
import com.sparta.homework.entity.UserRoleEnum;
import com.sparta.homework.service.CommentsService;
import com.sparta.homework.util.CheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
public class CommentsController {
    private final CommentsService commentsService;
    private final CheckUtil checkUtil;

    @PostMapping("/{id}")
    public CommentsResponseDto createComment(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        return commentsService.createComment(requestDto, id, utilDto.getUsername());
    }

    @PutMapping("/comments/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        //결국 분기문이 여기 들어가야 할 것. - 그러나 보통 API는 어드민용 / 유저용으로 나눔. -> 결국 API가 나눠진다.
        return commentsService.updateComment(requestDto, id, utilDto.getUsername());
    }
    //User반환하지 말구 인증 reponse(검증통과된 유저네임이과 유저롤). <- return을 이것으로 해보자//

    @PutMapping("/admin/comments/{id}")
    public String updateCommentAdmin(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        if (utilDto.getUserRoleEnum() == UserRoleEnum.USER) {
            throw new IllegalArgumentException("이것은 메시지다.");
        }
            return commentsService.updateCommentAdmin(requestDto, id);
    }

    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable Long id, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        return commentsService.deleteComment(id, utilDto.getUsername());
    }

    @DeleteMapping("/admin/comments/{id}")
    public String deleteCommentAdmin(@PathVariable Long id, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        if (utilDto.getUserRoleEnum() == UserRoleEnum.USER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "관리자만 사용 가능합니다");
        }
            return commentsService.deleteCommentAdmin(id, utilDto.getUsername());
    }
}
