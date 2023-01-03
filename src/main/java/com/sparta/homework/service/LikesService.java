package com.sparta.homework.service;

import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.entity.*;
import com.sparta.homework.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesCommentRepository likesCommentRepository;
    private final LikesMemoRepository likesMemoRepository;
    private final CommentsRepository commentsRepository;
    private final MemoRepository memoRepository;

// addOrDeleteComment

    @Transactional
    public CommentsResponseDto likeComment(Long id, User user) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트가 없습니다"));
        // likeRepository 에 이미 같은 사용자! 가 좋아요 한 것이 있다면 제거해야 해.
        if (likesCommentRepository.findByUserId(user.getId()).isEmpty()) {
            LikesComment likesComment = likesCommentRepository.saveAndFlush(new LikesComment(comments, user));
            comments.getLikes().add(likesComment);
            // 얘가 문제일까? comments에는 남고 likesRepository에서는 delete 를 쏜건가?
        } else if (likesCommentRepository.findByUserId(user.getId()).isPresent()) {
            likesCommentRepository.deleteByUserId(user.getId());
        }
        return CommentsResponseDto.from(comments);
    }

    // 동시성 문제 시도방안 1.Thread.sleep 사용해보기.
    // -> 코멘트 findById 이후에 thread 멈추면 어떻게되나? 다른 스레드가 멀티로 움직여도 멈췄던 스레드가 delete 하게되나?
    // 시험해보려면 동시성 문제를 일으킬 방법을 알아야 할텐데...Test 코드 작성을 오늘 공부하긴 해야겠다.

    // 시도방안 2.

    @Transactional
    public MemoResponseDto likeMemo(Long id, User user) {
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("메모가 없습니다"));
        if (likesMemoRepository.findByUserId(user.getId()).isEmpty()) {
            LikesMemo likesMemo = likesMemoRepository.saveAndFlush(new LikesMemo(memo, user));
            memo.getLikes().add(likesMemo);
        } else if (likesMemoRepository.findByUserId(user.getId()).isPresent()) {
            likesMemoRepository.deleteByUserId(user.getId());
        }
        return MemoResponseDto.from(memo);
    }
}