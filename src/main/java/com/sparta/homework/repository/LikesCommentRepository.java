package com.sparta.homework.repository;

import com.sparta.homework.entity.LikesComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesCommentRepository extends JpaRepository<LikesComment, Long> {
    Optional<LikesComment> deleteByUserIdAndCommentsId(Long userId, Long commentsId);
    int countLikesCommentByUserId(Long id);
    int countLikesCommentByUserIdAndCommentsId(Long userId, Long commentsId);
    int countLikesCommentByCommentsId(Long id);
}
