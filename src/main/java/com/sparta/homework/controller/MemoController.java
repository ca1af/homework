package com.sparta.homework.controller;

import com.sparta.homework.dto.DeleteRequestDto;
import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoService.getMemos();
    }

    @GetMapping("/api/memos/{id}")
    public Memo getCertainMemos(@PathVariable Long id){return memoService.getCertainMemo(id);}
// id 를 입력받는데 왜 안나올까?...PathVariable로 id 줬고...왜안되냐?
    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.update(id, requestDto);
    }

    //@RequestBody는 json으로 보냈을 때 객체 형태로 받아온다.
    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id, @RequestBody DeleteRequestDto requestDto) {
        return memoService.deleteMemo(id, requestDto.getPassword());
    }

}
