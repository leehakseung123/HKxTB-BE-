package com.study.springboot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalcRestController {

    @PostMapping("/api/calc")
    public Map<String, Object> calc(@RequestBody Map<String, Object> req) {
        Map<String, Object> res = new HashMap<>();
        try {
            // 클라이언트에서 전달한 JSON 데이터 예 : { "num1": 2, "num2": 3 }
            int num1 = ((Number) req.get("num1")).intValue();
            int num2 = ((Number) req.get("num2")).intValue();
            // 여기서는 덧셈 연산만 수행 (예제의 결과 예시 : 2+3=5)
            int result = num1 - num2;

            res.put("status", "ok");
            res.put("result", result);
        } catch (Exception e) {
            res.put("status", "error");
            res.put("result", "계산 에러");
        }
        return res;
    }
}
