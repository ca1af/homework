package com.sparta.homework.service;

import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Likes;
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
        }
        return CommentsResponseDto.from(comments);
    }


    // 지금 형태는 좋지는 않다. 눌렀을 때 like 다시누르면 delete의 유추가 어렵다.
    // 차라리 넣는거랑 빼는걸 따로 만들자.
    @Transactional
    public CommentsResponseDto deleteCommentLikes(Long id, User user){
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("X"));
        if (likesRepository.findByUserId(user.getId()).isPresent()) {
            likesRepository.deleteByUserId(user.getId());
        } else {
            throw new IllegalArgumentException("실패");
        }
        return CommentsResponseDto.from(comments);
    }
}