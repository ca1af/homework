package com.sparta.homework.service;

import com.sparta.homework.dto.DeleteRequestDto;
import com.sparta.homework.dto.MemoRequestDto;
import com.sparta.homework.entity.Memo;
import com.sparta.homework.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Delete;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }
    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional(readOnly = true)
    public Memo getCertainMemo(Long id){
        return memoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디 없음"));
//        return memoRepository.findMemoById(id);
    }
// 여기서 id를 찾기만 하고...안넘겨주나?


    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (requestDto.getPassword().equals(memo.getPassword())){
            memo.update(requestDto);
        } else
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        return memo.getId();
    }

    @Transactional
    public Long deleteMemo(Long id, String password) {
        return memoRepository.deleteByIdAndPassword(id, password);
        //일치 불일치 확인을 다시 한 번 해야함.(레포지터리에 들려서) "DTO" 아이디 패스워드 값 비교해보자. DTO : 객체를 물고 다니는 친구들
    }

}
