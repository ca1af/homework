package com.sparta.homework.service;

import com.sparta.homework.dto.CommentsRequestDto;
import com.sparta.homework.dto.CommentsResponseDto;
import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.entity.UserRoleEnum;
import com.sparta.homework.repository.CommentsRepository;
import com.sparta.homework.repository.LikesCommentRepository;
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
    private final LikesCommentRepository likesCommentRepository;

    public CommentsResponseDto createComment(CommentsRequestDto requestDto, Long id, String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(IllegalArgumentException::new);
        Memo memo = memoRepository.findById(id).orElseThrow(() ->  new IllegalArgumentException("해당하는 메모id가 없다."));
        Comments comment = commentsRepository.saveAndFlush(new Comments(requestDto, memo, user.getUsername()));
        return CommentsResponseDto.from(comment);
    }

    @Transactional
    public CommentsResponseDto updateComment(CommentsRequestDto requestDto, Long id, String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(IllegalArgumentException::new);
        Comments comment = commentsRepository.findByIdAndUserName(id, user.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자만 수정할 수 있습니다."));
        comment.update(requestDto);
        return CommentsResponseDto.from(comment);
    }

    @Transactional
    public CommentsResponseDto updateCommentAdmin(CommentsRequestDto requestDto, Long id) {
        Comments comment = commentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ADMIN - 댓글이 존재하지 않습니다."));
        comment.update(requestDto);
        return CommentsResponseDto.from(comment);
    }

    @Transactional
    public String deleteComment(Long id, String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(IllegalArgumentException::new);
        if (commentsRepository.findById(id).isPresent()){
            commentsRepository.findByIdAndUserName(id, user.getUsername()).orElseThrow(
                    () -> new IllegalArgumentException("작성자만 삭제할 수 있습니다."));

            commentsRepository.deleteCommentsByIdAndUserName(id, user.getUsername());
            likesCommentRepository.deleteAllByCommentsId(id);
        } else {
            throw new IllegalArgumentException("해당 코멘트가 없습니다");
        }

        return "삭제 완료";
    }


    @Transactional
    public String deleteCommentAdmin(Long id){


        commentsRepository.findById(id).orElseThrow(
                () ->  new IllegalArgumentException("ADMIN - 댓글이 존재하지 않습니다."));

        commentsRepository.deleteCommentsById(id);

        likesCommentRepository.deleteAllByCommentsId(id);
        return "삭제 완료";
    }
}
