package com.sparta.homework.controller;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.entity.User;
import com.sparta.homework.service.CommentsService;
import com.sparta.homework.util.CheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
public class CommentsController {
    private final CommentsService commentsService;
    private final CheckUtil checkUtil;

    @PostMapping("/{id}")
    public CommentsResponseDto createComment(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, HttpServletRequest request) {
        User user = checkUtil.tokenChecker(request);
        return commentsService.createComment(requestDto, id, user.getUsername());
    }

    @PutMapping("/comments/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto, HttpServletRequest request) {
        User user = checkUtil.tokenChecker(request);
        return commentsService.updateComment(requestDto, id, user.getUsername());
    }

    @DeleteMapping("/comments/{id}")
    public String deleteComment(@PathVariable Long id, HttpServletRequest request) {
        User user = checkUtil.tokenChecker(request);
        return commentsService.deleteMemo(id, user.getUsername());
    }
}
