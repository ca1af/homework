package com.sparta.homework.controller;

import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.security.UserDetailsImpl;
import com.sparta.homework.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.getMemos(userDetails.getUsername());
    }

    @GetMapping("/api/admin/memos")
    @PreAuthorize("hasRole('ADMIN')")
    public List<MemoResponseDto> getMemosAdmin() {
        return memoService.getMemosAdmin();
    }

    @GetMapping("/api/memos/{id}")
    public MemoResponseDto getCertainMemos(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.getCertainMemo(id, userDetails.getUsername());
    }

    @GetMapping("/api/admin/memos/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MemoResponseDto getCertainMemosAdmin(@PathVariable Long id) {
        return memoService.getCertainMemoAdmin(id);
    }

    @PutMapping("/api/memos/{id}")
    public String updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.update(id, requestDto, userDetails.getUsername());
    }

    @PutMapping("/api/admin/memos/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateMemoAdmin(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.updateAdmin(id, requestDto);
    }

    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.deleteMemo(id, userDetails.getUsername());
    }

    @DeleteMapping("/api/admin/memos/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteMemoAdmin(@PathVariable Long id) {
        return memoService.deleteMemoAdmin(id);
    }
}