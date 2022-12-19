package com.sparta.homework.repository;

import com.sparta.homework.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long>{
}
