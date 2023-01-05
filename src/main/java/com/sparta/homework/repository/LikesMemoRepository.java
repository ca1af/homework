package com.sparta.homework.repository;

import com.sparta.homework.entity.LikesMemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesMemoRepository extends JpaRepository<LikesMemo, Long> {

    void deleteByUserIdAndMemoId(Long userId, Long memoId);
    int countLikesMemoByUserIdAndMemoId(Long userId, Long memoId);
    int countLikesMemoByMemoId(Long id);
    void deleteAllByMemoId(Long memoId);
}
