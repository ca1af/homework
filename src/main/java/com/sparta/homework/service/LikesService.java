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
    public MemoResponseDto likeMemo(Long id, User user) {
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("메모가 없습니다"));
        if (likesMemoRepository.countLikesMemoByUserIdAndMemoId(user.getId(), memo.getId()) == 0) {
            likesMemoRepository.saveAndFlush(new LikesMemo(memo, user));
            memo.setLikesCount(likesMemoRepository.countLikesMemoByMemoId(memo.getId()));
        }
        return MemoResponseDto.from(memo);
    }

    @Transactional
    public MemoResponseDto deleteLikeMemo(Long id, User user) {
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("메모가 없습니다"));
        if (likesMemoRepository.countLikesMemoByUserIdAndMemoId(user.getId(), memo.getId()) != 0) {
            likesMemoRepository.deleteByUserIdAndMemoId(user.getId(), memo.getId());
            memo.setLikesCount(likesMemoRepository.countLikesMemoByMemoId(memo.getId()));
        }
        return MemoResponseDto.from(memo);
    }

    @Transactional
    public CommentsResponseDto likeComment(Long id, User user) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트가 없습니다"));
        // likeRepository 에 이미 같은 사용자! 가 좋아요 한 것이 있다면 제거해야 해.
        if (likesCommentRepository.countLikesCommentByUserIdAndCommentsId(user.getId(), comments.getId()) == 0) {
            likesCommentRepository.saveAndFlush(new LikesComment(comments, user));
            comments.setLikesCountComment(likesCommentRepository.countLikesCommentByCommentsId(user.getId()));
        }

        return CommentsResponseDto.from(comments);
    }

    @Transactional
    public CommentsResponseDto deleteLikeComment(Long id, User user) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트가 없습니다"));
        if (likesCommentRepository.countLikesCommentByUserIdAndCommentsId(user.getId(), comments.getId()) != 0) {
            likesCommentRepository.deleteByUserIdAndCommentsId(user.getId(), comments.getId());
            comments.setLikesCountComment(likesCommentRepository.countLikesCommentByCommentsId(user.getId()));
        }
        return CommentsResponseDto.from(comments);
    }

//    @Transactional
//    public CommentsResponseDto likeComment(Long id, User user) {
//        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트가 없습니다"));
//        // likeRepository 에 이미 같은 사용자! 가 좋아요 한 것이 있다면 제거해야 해.
//        if (likesCommentRepository.findByUserId(user.getId()).isEmpty()) {
//            LikesComment likesComment = likesCommentRepository.saveAndFlush(new LikesComment(comments, user));
//            comments.getLikes().add(likesComment);
//        } else if (likesCommentRepository.findByUserId(user.getId()).isPresent()) {
//            likesCommentRepository.deleteByUserId(user.getId());
//        }
//
//        return CommentsResponseDto.from(comments);
//    }
//
//
//        @Transactional
//    public CommentsResponseDto deleteLikeComment(Long id, User user) {
//        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 코멘트가 없습니다"));
//        if (likesCommentRepository.findByUserId(user.getId()).isPresent()) {
//            likesCommentRepository.deleteByUserId(user.getId());
//        }
//        return CommentsResponseDto.from(comments);
//    }

}