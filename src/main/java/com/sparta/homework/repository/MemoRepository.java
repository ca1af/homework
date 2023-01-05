package com.sparta.homework.repository;

import com.sparta.homework.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    void deleteMemoByUserIdAndId(Long id, Long userId);
    List<Memo> findAllByUserIdOrderByCreatedAtDesc(Long userId);
    Optional<Memo> findByIdAndUserId(Long id, Long userId);
    void deleteMemoById(Long id);
}
