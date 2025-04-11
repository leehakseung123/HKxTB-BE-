package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VmController {

    private final List<Product> productList = new ArrayList<>();
    private boolean isKorean = true;

    // [1] 상품 목록
    @GetMapping("/")
    public String productList(Model model) {
        model.addAttribute("products", productList);
        model.addAttribute("lang", isKorean);
        model.addAttribute("totalCount", productList.size());
        return "productList";
    }

    // [2] 상품 추가 폼
    @GetMapping("/addProductForm")
    public String showAddForm() {
        return "addProductForm";
    }

    // [3] 상품 추가 처리
    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("inputName") String name,
                             @RequestParam("inputPrice") int price,
                             @RequestParam("inputLimitDate") String limitDate) {
        LocalDate parsedDate = LocalDate.parse(limitDate);
        productList.add(new Product(name, price, parsedDate));
        return "redirect:/?added";
    }

    // [4] 상품 수정 폼
    @GetMapping("/editProductForm")
    public String editForm(@RequestParam("index") int index, Model model) {
        Product product = productList.get(index);
        model.addAttribute("product", product);
        model.addAttribute("index", index);
        return "editProductForm";
    }

    // [5] 상품 수정 처리
    @PostMapping("/editProduct")
    public String editProduct(@RequestParam("index") int index,
                              @RequestParam("inputName") String name,
                              @RequestParam("inputPrice") int price,
                              @RequestParam("inputLimitDate") String limitDate) {
        LocalDate parsedDate = LocalDate.parse(limitDate);
        productList.set(index, new Product(name, price, parsedDate));
        return "redirect:/?edited";
    }

    // [6] 상품 삭제
    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("index") int index) {
        if (index >= 0 && index < productList.size()) {
            productList.remove(index);
        }
        return "redirect:/?deleted";
    }

    // [언어 토글] - 필요시 사용
    @GetMapping("/toggleLang")
    public String toggleLang() {
        isKorean = !isKorean;
        return "redirect:/";
    }
}
