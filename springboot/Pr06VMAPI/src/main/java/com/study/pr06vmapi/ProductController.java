package com.study.pr06vmapi;


import com.study.pr06vmapi.Product;
import com.study.pr06vmapi.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 전체 상품 조회
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    // 단일 상품 조회
    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable int productId) {
        return productService.findById(productId);
    }

    // 상품 등록
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    // 상품 수정
    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable int productId, @RequestBody Product product) {
        return productService.update(productId, product);
    }

    // 상품 삭제
    @DeleteMapping("/{productId}")
    public boolean deleteProduct(@PathVariable int productId) {
        return productService.delete(productId);
    }
}

