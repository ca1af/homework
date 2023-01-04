package com.sparta.homework.repository;

import com.sparta.homework.entity.LikesComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesCommentRepository extends JpaRepository<LikesComment, Long> {
    void deleteByUserIdAndCommentsId(Long userId, Long commentsId);
    int countLikesCommentByUserId(Long id);
    int countLikesCommentByUserIdAndCommentsId(Long userId, Long commentsId);
    int countLikesCommentByCommentsId(Long id);
}
