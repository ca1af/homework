package com.sparta.homework.repository;

import com.sparta.homework.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc();
    Long deleteByIdAndPassword(Long id, String password);
}
