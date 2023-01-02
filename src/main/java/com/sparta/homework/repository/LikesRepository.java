package com.sparta.homework.repository;

import com.sparta.homework.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByCommentsId(Long id);
    Optional<Likes> findByUserId(Long id);
    Optional<Likes> deleteByUserId(Long id);
}
