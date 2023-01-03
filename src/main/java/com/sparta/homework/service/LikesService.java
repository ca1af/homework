package com.sparta.homework.service;

import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Likes;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.repository.CommentsRepository;
import com.sparta.homework.repository.LikesRepository;
import com.sparta.homework.repository.MemoRepository;
import com.sparta.homework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final CommentsRepository commentsRepository;
    private final MemoRepository memoRepository;

// addOrDeleteComment

    @Transactional
    public CommentsResponseDto likeComment(Long id, User user) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트가 없습니다"));
        // likeRepository 에 이미 같은 사용자! 가 좋아요 한 것이 있다면 제거해야 해.
        if (likesRepository.findByUserId(user.getId()).isEmpty()) {
            Likes likes = likesRepository.saveAndFlush(new Likes(comments,user));
            comments.getLikes().add(likes);
            // 얘가 문제일까? comments에는 남고 likesRepository에서는 delete 를 쏜건가?
        } else if (likesRepository.findByUserId(user.getId()).isPresent()) {
            likesRepository.deleteByUserId(user.getId());
        }
        return CommentsResponseDto.from(comments);
    }
}