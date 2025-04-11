package com.study.springboot;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final List<Member> members = new ArrayList<>();

    public MemberService() {
        // 초기 회원 데이터
        Member m1 = new Member();
        m1.setUsername("admin");
        m1.setPassword("1234");
        m1.setEmail("admin@gmail.com");
        m1.setJoindate(LocalDate.of(2024, 3, 29));

        Member m2 = new Member();
        m2.setUsername("hong");
        m2.setPassword("1234");
        m2.setEmail("hong@gmail.com");
        m2.setJoindate(LocalDate.of(2024, 3, 29));

        members.add(m1);
        members.add(m2);
    }

    public List<Member> getAllMembers() {
        return members;
    }

    public Member getMember(int index) {
        return members.get(index);
    }

    public void update(int index, Member member) {
        if (index >= 0 && index < members.size()) {
            members.set(index, member);
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < members.size()) {
            members.remove(index);
        }
    }
}
