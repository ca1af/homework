package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.security.UserDetailsImpl;
import com.sparta.homework.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/comments/{id}")
    public CommentsResponseDto likeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.likeComment(id,userDetails.getUser());
    }

    @DeleteMapping("/delete/comments/{id}")
    public CommentsResponseDto deleteLikeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.deleteLikeComment(id,userDetails.getUser());
    }

    @PostMapping("/memos/{id}")
    public MemoResponseDto likeMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.likeMemo(id,userDetails.getUser());
    }

    @DeleteMapping("/delete/memos/{id}")
    public MemoResponseDto deleteLikeMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.deleteLikeMemo(id,userDetails.getUser());
    }
}