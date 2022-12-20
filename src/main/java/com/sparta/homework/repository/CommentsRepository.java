package com.sparta.homework.repository;

import com.sparta.homework.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Long>{
    Optional<Comments> findByIdAndUserId(Long id, Long userId);
    Optional<Comments> deleteCommentsByIdAndUserId(Long id, Long userId);
    Optional<Comments> deleteCommentsById(Long id);
}
