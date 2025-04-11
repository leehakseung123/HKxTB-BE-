package com.study.springboot;

import com.study.springboot.Member;
import com.study.springboot.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;


@Controller
public class MemberController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @Autowired
    private MemberService memberService;

    // 회원가입 폼 이동
    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String doJoin(@RequestParam String inputName,
                         @RequestParam String inputPw,
                         @RequestParam String inputEmail) {
        Member member = new Member();
        member.setUsername(inputName);
        member.setPassword(inputPw);
        member.setEmail(inputEmail);

        memberService.register(member);

        return "redirect:/login";
    }

    // 로그인 폼 이동
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String doLogin(@RequestParam String inputName,
                          @RequestParam String inputPw,
                          Model model) {
        boolean isValid = memberService.login(inputName, inputPw);
        model.addAttribute("loginResult", isValid);
        return "login"; // "loginForm" → "login" 으로 수정
    }


    // 중복확인 API (REST 방식)
    @ResponseBody
    @GetMapping("/check-duplicate")
    public Map<String, Object> checkDuplicate(@RequestParam String username) {
        boolean duplicated = memberService.isDuplicated(username);
        Map<String, Object> result = new HashMap<>();
        result.put("status", duplicated ? "duplicated" : "ok");
        return result;
    }
}
