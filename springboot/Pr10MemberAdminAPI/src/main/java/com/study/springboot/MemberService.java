package com.study.springboot;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    private final List<Member> members = new ArrayList<>();

    public List<Member> getAllMembers() {
        return members;
    }

    public void register(Member member) {
        members.add(member);
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
