package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.service.CommentsService;
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

    @PutMapping("/comments/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, HttpServletRequest request){
        return commentsService.updateComment(requestDto,id,request);
    }

    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable Long id, HttpServletRequest request){
        return commentsService.deleteMemo(id,request);
    }
}
