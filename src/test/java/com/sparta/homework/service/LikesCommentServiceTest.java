package com.sparta.homework.service;

import com.sparta.homework.repository.LikesCommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class LikesCommentServiceTest {
    @Autowired
    private final LikesService likesService;
    @Autowired
    private final LikesCommentRepository likesCommentRepository;

    LikesCommentServiceTest(LikesService likesService, LikesCommentRepository likesCommentRepository) {
        this.likesService = likesService;
        this.likesCommentRepository = likesCommentRepository;
    }

    @Test
    void like() {
//        //given
//        User user = new User("user1", "12345!", UserRoleEnum.USER);
//        MemoRequestDto memoRequestDto = new MemoRequestDto("name", "title", "contents");
//        Memo memo = new Memo(memoRequestDto, user);
//        CommentsRequestDto commentsRequestDto = new CommentsRequestDto("comment");
//        Comments comments = new Comments(commentsRequestDto, memo, user.getUsername());
//        //when
//        likesService.likeComment(comments.getId());

        //then
//        assertNotNull(likesCommentRepository.findByUserId(comments.getId()));
    }
}