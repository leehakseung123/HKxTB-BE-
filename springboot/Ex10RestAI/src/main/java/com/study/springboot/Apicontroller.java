package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController : @Controller + @ResponseBody
@RestController
//@Controller
//@ResponseBody  // 클래스의 모든 응답이 문자열(JSON)으로 선언됨.
@RequestMapping("/api/v1")  //모든 경로가 /api/v1 부터 시작됨.
public class Apicontroller {
    //http://localhost:8080/api/v1/login
    @RequestMapping("/hello")
    public String hello(){
        return "API 서버입니다.";
    }
    // 클라 ---> 서버
    //      JSON  ->    DTO 클래스 매핑
    //   <- JSON
    @PostMapping("/login")
    @ResponseBody
    public ResDTO login(@RequestBody ReqDTO reqDTO){
        // HTTP 요청의 BODY 데이터를 reqDTO에 매핑한다.
        System.out.println("username = " + reqDTO.getUsername());
        System.out.println("password = " + reqDTO.getPassword());

        // DB에서 select해서 같은id/pw OK, 없으면 Fail 해야됨.

        ResDTO resDTO = new ResDTO();
        resDTO.setStatus("OK");
        resDTO.setMessage("로그인 성공");

        return resDTO; // HTTP 응답에 BODY에 실어서 보낸다.

    }
}