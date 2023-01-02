package com.sparta.homework.service;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.entity.UserRoleEnum;
import com.sparta.homework.repository.LikesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
class LikesServiceTest {
    @Autowired
    private final LikesService likesService;
    @Autowired
    private final LikesRepository likesRepository;

    LikesServiceTest(LikesService likesService, LikesRepository likesRepository) {
        this.likesService = likesService;
        this.likesRepository = likesRepository;
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
//        assertNotNull(likesRepository.findByUserId(comments.getId()));
    }
}