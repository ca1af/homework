package com.sparta.homework.repository;

import com.sparta.homework.entity.LikesComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesCommentRepository extends JpaRepository<LikesComment, Long> {
    Optional<LikesComment> findByUserId(Long id);
    Optional<LikesComment> deleteByUserId(Long id);
    int countLikesCommentByUserId(Long id);
    int countLikesCommentByCommentsId(Long id);
}
