package com.sparta.homework.repository;

import com.sparta.homework.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Long>{
    Optional<Comments> findByIdAndUserName(Long id, String userName);
    Optional<Comments> deleteCommentsByIdAndUserName(Long id, String userName);
    Optional<Comments> deleteCommentsById(Long id);
}
