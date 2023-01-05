package com.sparta.homework.service;

import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.entity.Comments;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.repository.LikesCommentRepository;
import com.sparta.homework.repository.LikesMemoRepository;
import com.sparta.homework.repository.MemoRepository;
import com.sparta.homework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final LikesMemoRepository likesMemoRepository;
    private final LikesCommentRepository likesCommentRepository;

    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {

        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                ()-> new IllegalArgumentException("유저이름이 일치하지 않습니다"));

        Memo memo = memoRepository.saveAndFlush(new Memo(requestDto, user));

        return MemoResponseDto.from(memo);
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        List<Memo> memos = memoRepository.findAllByUserIdOrderByCreatedAtDesc(user.get().getId());
        return memos.stream().map(MemoResponseDto::from).collect(Collectors.toList());
//        return memos.stream().map(MemoResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemosAdmin() {
        List<Memo> memos = memoRepository.findAll();
        return memos.stream().map(MemoResponseDto::from).collect(Collectors.toList());
//        return memos.stream().map(MemoResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemoResponseDto getCertainMemo(Long id, String userName) {
        Optional<User> user = userRepository.findByUsername(userName);

        Memo memo = memoRepository.findByIdAndUserId(id, user.get().getId())
                .orElseThrow(() -> new IllegalArgumentException("작성자만 조회할 수 있습니다."));

        return MemoResponseDto.from(memo);
    }

    @Transactional(readOnly = true)
    public MemoResponseDto getCertainMemoAdmin(Long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ADMIN - 해당 메모 없음"));
        return MemoResponseDto.from(memo);
    }

    @Transactional
    public String update(Long id, MemoRequestDto requestDto, String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new IllegalArgumentException("작성자만 수정할 수 있습니다."));
        memo.update(requestDto);
        return "수정 완료";
    }

    @Transactional
    public String updateAdmin(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ADMIN - 메모가 존재하지 않습니다."));
        memo.update(requestDto);
        return "수정완료";
    }

    @Transactional
    public String deleteMemo(Long id, String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new IllegalArgumentException("메시지"));

        Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("작성자만 삭제할 수 있습니다.")
        );

        memoRepository.deleteMemoByUserIdAndId(memo.getId(), user.getId());
        likesMemoRepository.deleteAllByMemoId(memo.getId());

        List<Comments> commentsList = memo.getComments();
        for (Comments comments : commentsList) {
            likesCommentRepository.deleteAllByCommentsId(comments.getId());
        } // for 문으로 모든 커맨츠 삭제하는 친구.

        return "삭제완료";
    }


    @Transactional
    public String deleteMemoAdmin(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ADMIN - 메모가 존재하지 않습니다.")
        );
        memoRepository.deleteMemoById(memo.getId());
        likesMemoRepository.deleteAllByMemoId(memo.getId());

        List<Comments> commentsList = memo.getComments();
        for (Comments comments : commentsList) {
            likesCommentRepository.deleteAllByCommentsId(comments.getId());
        } // for 문으로 모든 커맨츠 삭제하는 친구.
        return "삭제완료";
    }
}


