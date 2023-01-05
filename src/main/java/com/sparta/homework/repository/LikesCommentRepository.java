package com.sparta.homework.repository;

import com.sparta.homework.entity.LikesComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesCommentRepository extends JpaRepository<LikesComment, Long> {
    void deleteByUserIdAndCommentsId(Long userId, Long commentsId);
    int countLikesCommentByUserIdAndCommentsId(Long userId, Long commentsId);
    int countLikesCommentByCommentsId(Long id);
    void deleteAllByCommentsId(Long commentId);
}
