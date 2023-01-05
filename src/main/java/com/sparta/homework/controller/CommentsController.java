package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.security.UserDetailsImpl;
import com.sparta.homework.service.CommentsService;
import com.sparta.homework.service.MemoService;
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
    public CommentsResponseDto updateComment(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.updateComment(requestDto, id, userDetails.getUsername());
    }

    @PutMapping("/admin/comments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CommentsResponseDto updateCommentAdmin(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto) {
            return commentsService.updateCommentAdmin(requestDto, id);
    }

    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.deleteComment(id, userDetails.getUsername());
    }

    @DeleteMapping("/admin/comments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCommentAdmin(@PathVariable Long id) {
            return commentsService.deleteCommentAdmin(id);
    }
}
