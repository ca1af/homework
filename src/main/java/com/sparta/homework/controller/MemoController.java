package com.sparta.homework.controller;

import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.dto.UtilDto;
import com.sparta.homework.entity.UserRoleEnum;
import com.sparta.homework.service.MemoService;
import com.sparta.homework.util.CheckUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        UtilDto utilDto = checkUtil.tokenChecker(request);
        return memoService.getMemos(utilDto.getUsername());
    }

    @GetMapping("/api/admin/memos")
    public List<MemoResponseDto> getMemosAdmin(HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        if (utilDto.getUserRoleEnum() == UserRoleEnum.USER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "관리자만 사용 가능합니다");
        }
        return memoService.getMemosAdmin();
    }

    @GetMapping("/api/memos/{id}")
    public MemoResponseDto getCertainMemos(@PathVariable Long id, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        return memoService.getCertainMemo(id, utilDto.getUsername());
    }

    @GetMapping("/api/admin/memos/{id}")
    public MemoResponseDto getCertainMemosAdmin(@PathVariable Long id, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        if (utilDto.getUserRoleEnum() == UserRoleEnum.USER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "관리자만 사용 가능합니다");
        }
        return memoService.getCertainMemoAdmin(id);
    }

    @PutMapping("/api/memos/{id}")
    public String updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        return memoService.update(id, requestDto, utilDto.getUsername());
    }

    @PutMapping("/api/admin/memos/{id}")
    public String updateMemoAdmin(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        if (utilDto.getUserRoleEnum() == UserRoleEnum.USER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "관리자만 사용 가능합니다");
        }
        return memoService.updateAdmin(id, requestDto);
    }

    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        return memoService.deleteMemo(id, utilDto.getUsername());
    }

    @DeleteMapping("/api/admin/memos/{id}")
    public String deleteMemoAdmin(@PathVariable Long id, HttpServletRequest request) {
        UtilDto utilDto = checkUtil.tokenChecker(request);
        if (utilDto.getUserRoleEnum() == UserRoleEnum.USER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "관리자만 사용 가능합니다");
        }
        return memoService.deleteMemoAdmin(id);
    }
}