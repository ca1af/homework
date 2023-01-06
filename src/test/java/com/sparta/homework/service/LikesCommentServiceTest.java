//package com.sparta.homework.service;
//
//import com.sparta.homework.dto.CommentsRequestDto;
//import com.sparta.homework.dto.MemoRequestDto;
//import com.sparta.homework.entity.Comments;
//import com.sparta.homework.entity.Memo;
//import com.sparta.homework.entity.User;
//import com.sparta.homework.entity.UserRoleEnum;
//import com.sparta.homework.repository.CommentsRepository;
//import com.sparta.homework.repository.LikesCommentRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 클래스마다 쓰레드 인스턴스 생성하겠다.
//class LikesCommentServiceTest {
//    @InjectMocks
//    private LikesService likesService;
//    @Mock // SpyBean도 한번 찾ㅇ보자
//    private LikesCommentRepository likesCommentRepository;
//    @Mock
//    private CommentsRepository commentsRepository;
//    @BeforeAll
//    void init() {
//        MockitoAnnotations.openMocks(this); 	}
//    @Test
//    void like() {
//        //given
//        User user = new User("user1", "12345!", UserRoleEnum.USER);
//        MemoRequestDto memoRequestDto = new MemoRequestDto("name", "title", "contents");
//        Memo memo = new Memo(memoRequestDto, user);
//        CommentsRequestDto commentsRequestDto = new CommentsRequestDto("comment");
//        Comments comments = new Comments(commentsRequestDto, memo, user.getUsername());
//        when(commentsRepository.findById(comments.getId())).thenReturn(Optional.of(comments));
//
//        //when
//        likesService.likeComment(comments.getId(),user);
//
//        //then
//
////        then(likesCommentRepository).should(times(1)).save(message);
//        verify(likesCommentRepository, times(1)).countLikesCommentByCommentsId(comments.getId());
//    }
//}