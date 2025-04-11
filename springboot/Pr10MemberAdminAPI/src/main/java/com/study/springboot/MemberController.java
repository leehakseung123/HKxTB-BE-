package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String redirectToAdmin() {
        return "redirect:/admin";
    }

    // 관리자 페이지 (회원 목록)
    @GetMapping("/admin")
    public String showAdminPage(Model model,
                                @RequestParam(value = "status", required = false) String status) {
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("status", status); // 수정/삭제 결과 처리용
        return "admin";
    }

    // 수정 폼 이동
    @GetMapping("/update-form")
    public String showUpdateForm(@RequestParam int index, Model model) {
        Member member = memberService.getMember(index);
        model.addAttribute("member", member);
        model.addAttribute("index", index);
        return "update-form";
    }

    // 수정 처리
    @PostMapping("/update-action")
    public String updateMember(@RequestParam int index,
                               @RequestParam String inputName,
                               @RequestParam String inputPw,
                               @RequestParam String inputEmail,
                               @RequestParam String inputJoindate) {
        Member member = new Member();
        member.setUsername(inputName);
        member.setPassword(inputPw);
        member.setEmail(inputEmail);
        member.setJoindate(LocalDate.parse(inputJoindate));
        memberService.update(index, member);
        return "redirect:/admin?status=updated";
    }

    // 삭제 처리
    @GetMapping("/delete")
    public String deleteMember(@RequestParam int index) {
        memberService.delete(index);
        return "redirect:/admin?status=deleted";
    }
}
