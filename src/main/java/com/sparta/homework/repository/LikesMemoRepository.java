package com.sparta.homework.repository;

import com.sparta.homework.entity.LikesComment;
import com.sparta.homework.entity.LikesMemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesMemoRepository extends JpaRepository<LikesMemo, Long> {
    Optional<LikesMemo> findByUserId(Long id);
    Optional<LikesMemo> deleteByUserId(Long id);
}
