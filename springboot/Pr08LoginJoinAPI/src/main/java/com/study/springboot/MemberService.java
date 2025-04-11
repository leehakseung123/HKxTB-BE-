package com.study.springboot;

import com.study.springboot.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final List<Member> members = new ArrayList<>();

    // 회원가입
    public void register(Member member) {
        member.setJoindate(LocalDate.now());
        members.add(member);
    }

    // 로그인
    public boolean login(String username, String password) {
        return members.stream()
                .anyMatch(m -> m.getUsername().equals(username) && m.getPassword().equals(password));
    }

    
    // 중복확인
    public boolean isDuplicated(String username) {
        return members.stream()
                .anyMatch(m -> m.getUsername().equals(username));
    }


    // 전체 리스트 (옵션)
    public List<Member> getAllMembers() {
        return members;
    }
}