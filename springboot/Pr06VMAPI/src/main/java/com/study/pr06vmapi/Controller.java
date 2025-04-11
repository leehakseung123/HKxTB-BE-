package com.study.pr06vmapi;

import org.springframework.web.bind.annotation.GetMapping;

public class Controller {
    @GetMapping("/index")
    public String indexPage() {
        // templates/index.html (Thymeleaf) 렌더링 가정
        return "index";
    }
}
