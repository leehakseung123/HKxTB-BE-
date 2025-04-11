package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemberApiController {
    @Autowired
    private MemberService memberService;

    //회원가입 API
    @PostMapping("/join")
    public Map<String, Object> join(@RequestBody Member member) {
        Map<String, Object> result = new HashMap<>();
        if (memberService.isDuplicated(member.getUsername())){
            result.put("status", "duplicated");
        } else {
            memberService.register(member);
            result.put("status", "ok");
        }
        return result;
    }

    // 로그인 API
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        boolean status = memberService.login(username, password);
        Map<String, Object> result = new HashMap<>();
        result.put("status", status ? "ok" : "fail");
        return  result;
    }
}
