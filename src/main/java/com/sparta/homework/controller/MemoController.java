package com.sparta.homework.controller;

import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.dto.MessageDto;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/api/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        return memoService.createMemo(requestDto, request);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos(HttpServletRequest request) {
        return memoService.getMemos(request);
    }

    @GetMapping("/api/memos/{id}")
    public Memo getCertainMemos(@PathVariable Long id){
        return memoService.getCertainMemo(id);
    }

// id 를 입력받는데 왜 안나올까?...PathVariable로 id 줬고...왜안되냐?

    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        return memoService.update(id, requestDto,request);
    }

    //@RequestBody는 json으로 보냈을 때 객체 형태로 받아온다.

    @DeleteMapping("/api/memos/{id}")
    public MessageDto deleteMemo(@PathVariable Long id, HttpServletRequest request) {
        return memoService.deleteMemo(id, request);
    }
}