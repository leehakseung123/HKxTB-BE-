package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberApiController {

    @Autowired
    private MemberService memberService;

    // 전체 회원 조회
    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    // 회원 등록
    @PostMapping("/members")
    public Map<String, Object> addMember(@RequestBody Member member) {
        memberService.register(member);
        Map<String, Object> res = new HashMap<>();
        res.put("status", "ok");
        return res;
    }

    // 회원 수정
    @PutMapping("/members/{index}")
    public Map<String, Object> updateMember(@PathVariable int index,
                                            @RequestBody Member member) {
        memberService.update(index, member);
        Map<String, Object> res = new HashMap<>();
        res.put("status", "ok");
        return res;
    }

    // 회원 삭제
    @DeleteMapping("/members/{index}")
    public Map<String, Object> deleteMember(@PathVariable int index) {
        memberService.delete(index);
        Map<String, Object> res = new HashMap<>();
        res.put("status", "ok");
        return res;
    }
}
