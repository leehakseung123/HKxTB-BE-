package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberApiController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> list() {
        return memberService.getAllMembers();
    }

    @PutMapping("/{index}")
    public Map<String, String> update(@PathVariable int index, @RequestBody Member member) {
        memberService.update(index, member);
        return Map.of("status", "ok");
    }

    @DeleteMapping("/{index}")
    public Map<String, String> delete(@PathVariable int index) {
        memberService.delete(index);
        return Map.of("status", "ok");
    }


}
