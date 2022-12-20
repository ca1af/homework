package com.sparta.homework.controller;

import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.entity.User;
import com.sparta.homework.service.MemoService;
import com.sparta.homework.util.CheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    private final CheckUtil checkUtil;

    @PostMapping("/api/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        checkUtil.tokenChecker(request);
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos(HttpServletRequest request) {
        User user = checkUtil.tokenChecker(request);
        return memoService.getMemos(user.getUsername());
    }

    @GetMapping("/api/memos/{id}")
    public MemoResponseDto getCertainMemos(@PathVariable Long id, HttpServletRequest request) {
        User user = checkUtil.tokenChecker(request);
        return memoService.getCertainMemo(id, user.getUsername());
    }

// id 를 입력받는데 왜 안나올까?...PathVariable로 id 줬고...왜안되냐?

    @PutMapping("/api/memos/{id}")
    public String updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        User user = checkUtil.tokenChecker(request);
        return memoService.update(id, requestDto, user.getUsername());
    }

    //@RequestBody는 json으로 보냈을 때 객체 형태로 받아온다.

    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, HttpServletRequest request) {
        User user = checkUtil.tokenChecker(request);
        return memoService.deleteMemo(id, user.getUsername());
    }
}