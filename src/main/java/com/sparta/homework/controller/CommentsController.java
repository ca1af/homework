package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.security.UserDetailsImpl;
import com.sparta.homework.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
public class CommentsController {
    private final CommentsService commentsService;

    @PostMapping("/{id}")
    public CommentsResponseDto createComment(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.createComment(requestDto, id, userDetails.getUsername());
    }

    @PutMapping("/comments/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.updateComment(requestDto, id, userDetails.getUsername());
    }
    //User반환하지 말구 인증 reponse(검증통과된 유저네임이과 유저롤). <- return을 이것으로 해보자//

    @PutMapping("/admin/comments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCommentAdmin(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto) {
            return commentsService.updateCommentAdmin(requestDto, id);
    }

    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.deleteComment(id, userDetails.getUsername());
    }

    @DeleteMapping("/admin/comments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCommentAdmin(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
            return commentsService.deleteCommentAdmin(id, userDetails.getUsername());
    }
}
