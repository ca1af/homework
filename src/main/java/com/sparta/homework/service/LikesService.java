package com.sparta.homework.service;

import com.sparta.homework.dto.LikesResponseDto;
import com.sparta.homework.entity.Comments;
import com.sparta.homework.repository.CommentsRepository;
import com.sparta.homework.repository.LikesRepository;
import com.sparta.homework.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final CommentsRepository commentsRepository;
    private final MemoRepository memoRepository;

    public LikesResponseDto likeComment(Long id){
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트가 없습니다"));
        comments.getLIkesList();
        return null;
    }
}
