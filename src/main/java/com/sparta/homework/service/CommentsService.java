package com.sparta.homework.service;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.entity.UserRoleEnum;
import com.sparta.homework.repository.CommentsRepository;
import com.sparta.homework.repository.MemoRepository;
import com.sparta.homework.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public CommentsResponseDto createComment(CommentsRequestDto requestDto, Long id, String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        Memo memo = memoRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당하는 메모id가 없다."));
        Comments comment = commentsRepository.saveAndFlush(new Comments(requestDto, memo, user.get().getUsername()));
        return CommentsResponseDto.from(comment);
    }

    @Transactional
    public String updateComment(CommentsRequestDto requestDto, Long id, String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        Comments comment = commentsRepository.findByIdAndUserName(id, user.get().getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자만 수정할 수 있습니다."));
        comment.update(requestDto);
        return "수정 완료";
    }

    @Transactional
    public String updateCommentAdmin(CommentsRequestDto requestDto, Long id) {
        Comments comment = commentsRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ADMIN - 댓글이 존재하지 않습니다."));
        comment.update(requestDto);
        return "ADMIN-수정완료";
    }

    @Transactional
    public String deleteComment(Long id, String userName) {
        Optional<User> user = userRepository.findByUsername(userName);

        commentsRepository.findByIdAndUserName(id, user.get().getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자만 삭제할 수 있습니다."));

        commentsRepository.deleteCommentsByIdAndUserName(id, user.get().getUsername());
        return "삭제 완료";
    }


    @Transactional
    public String deleteCommentAdmin(Long id, String userName){
        Optional<User> user = userRepository.findByUsername(userName);

        commentsRepository.findById(id).orElseThrow(
                () ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "ADMIN - 댓글이 존재하지 않습니다."));

        commentsRepository.deleteCommentsById(id);
        return "삭제 완료";
    }
}
