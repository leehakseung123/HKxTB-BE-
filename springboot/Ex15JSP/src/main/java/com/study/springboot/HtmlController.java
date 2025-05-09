package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/")
    public String main() {
        return "ex01"; // → /WEB-INF/views/ex01.jsp
    }
}
