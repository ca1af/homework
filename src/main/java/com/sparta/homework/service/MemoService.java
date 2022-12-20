package com.sparta.homework.service;

import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.dto.MemoResponseDto;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.entity.User;
import com.sparta.homework.entity.UserRoleEnum;
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

    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {

        Optional<User> user = userRepository.findByUsername(requestDto.getUsername());

        Memo memo = memoRepository.saveAndFlush(new Memo(requestDto, user.get(), user.get().getUsername()));

        return MemoResponseDto.from(memo);
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);

        UserRoleEnum userRoleEnum = user.get().getRole();
        System.out.println("role = " + userRoleEnum);

        List<Memo> memos;
        if (userRoleEnum == UserRoleEnum.USER) {
            memos = memoRepository.findAllByUserIdOrderByCreatedAtDesc(user.get().getId());
        } else {
            memos = memoRepository.findAll();
        }
        return memos.stream().map(MemoResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemoResponseDto getCertainMemo(Long id, String userName) {

        Optional<User> user = userRepository.findByUsername(userName);

        UserRoleEnum userRoleEnum = user.get().getRole();

        Memo memo;
        if (userRoleEnum == UserRoleEnum.USER) {
            memo = memoRepository.findByIdAndUserId(id, user.get().getId())
                    .orElseThrow(() ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자만 조회할 수 있습니다."));
        } else {
            memo = memoRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ADMIN - 해당 메모 없음"));
        }
        return MemoResponseDto.from(memo);
    }

    @Transactional
    public String update(Long id, MemoRequestDto requestDto, String userName) {


        Optional<User> user = userRepository.findByUsername(userName);

        UserRoleEnum userRoleEnum = user.get().getRole();
        Memo memo;
        if (userRoleEnum == UserRoleEnum.USER) {
            memo = memoRepository.findByIdAndUserId(id, user.get().getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자만 수정할 수 있습니다.")
            );
            memo.update(requestDto);
        } else {
            memo = memoRepository.findById(id).orElseThrow(
                    () ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "ADMIN - 메모가 존재하지 않습니다.")
            );
            memo.update(requestDto);
        }

        return "수정 완료";
    }

    @Transactional
    public String deleteMemo(Long id, String userName) {
        Optional<User> user = userRepository.findByUsername(userName);

        UserRoleEnum userRoleEnum = user.get().getRole();
        Memo memo;

        if (userRoleEnum == UserRoleEnum.USER) {
            memo = memoRepository.findByIdAndUserId(id, user.get().getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "작성자만 삭제할 수 있습니다.")
            );
            memoRepository.deleteMemoByUserIdAndId(memo.getId(), user.get().getId());
        } else {
            memo = memoRepository.findById(id).orElseThrow(
                    () ->  new ResponseStatusException(HttpStatus.BAD_REQUEST, "ADMIN - 메모가 존재하지 않습니다.")
            );
            memoRepository.deleteMemoById(memo.getId());
        }
        return "삭제완료";
    }
}


