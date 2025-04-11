package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalcController {

    @GetMapping("/calc")
    public String calc(@RequestParam(required = false) String num1,
                       @RequestParam(required = false) String num2,
                       @RequestParam(required = false) String op,
                       Model model) {
        int result = 0;
        int n1 = 0, n2 = 0;

        try {
            if (num1 != null) n1 = Integer.parseInt(num1);
            if (num2 != null) n2 = Integer.parseInt (num2);

            switch (op) {
                case "plus": result = n1 + n2; break;
                case "minus": result = n1 - n2; break;
                case "mul": result = n1 * n2; break;
                case "div": result = n2 != 0 ? n1 / n2 : 0; break;
            }

            model.addAttribute("num1", num1);
            model.addAttribute("num2", num2);
            model.addAttribute("result", result);

        } catch (Exception e) {
            model.addAttribute("result", "계산 오류");
        }

        return "index";
    }
}
