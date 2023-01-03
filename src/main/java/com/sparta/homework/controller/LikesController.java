package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.security.UserDetailsImpl;
import com.sparta.homework.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/comments/{id}")
    public CommentsResponseDto likeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        //userDetails로 요청받은 유저가 이미 likeService...?
        return likesService.likeComment(id,userDetails.getUser());
    }

    @PostMapping("/memos/{id}")
    public MemoResponseDto likeMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.likeMemo(id,userDetails.getUser());
    }

//    @PostMapping("/comments/delete/{id}")
//    public CommentsResponseDto deleteCommentLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return likesService.deleteCommentLikes(id,userDetails.getUser());
//    }
}