package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.service.CommentsService;
import com.sparta.homework.service.MemoService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
public class CommentsController {
    private final CommentsService commentsService;
    @PostMapping("/{id}")
    public CommentsResponseDto createComment(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, HttpServletRequest request){
        return commentsService.createComment(requestDto,id,request);
    }
}
