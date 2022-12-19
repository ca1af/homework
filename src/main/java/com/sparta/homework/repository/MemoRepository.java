package com.sparta.homework.repository;

import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {

//    Long deleteByUserId(Long userId);
    Optional<Memo> deleteMemoByUserIdAndId(Long id, Long userId);
    List<Memo> findAllByUserIdOrderByCreatedAtDesc(Long userId);
    Optional<Memo> findByIdAndUserId(Long id, Long userId);
    Optional<Memo> deleteMemoById(Long id);

}
