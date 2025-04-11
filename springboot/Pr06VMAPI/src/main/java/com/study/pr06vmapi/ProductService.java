package com.study.pr06vmapi;

import com.study.pr06vmapi.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    // DB 대신 ArrayList 로 메모리 상에 데이터 저장
    private final List<Product> products = new ArrayList<>();
    private static int sequence = 0; // productId 자동 증가용

    // 테스트용 초기 데이터
    public ProductService() {
        products.add(new Product(++sequence, "콜라", LocalDate.of(2023, 12, 30)));
        products.add(new Product(++sequence, "사이다", LocalDate.of(2023, 12, 31)));
    }

    // 전체 상품 조회
    public List<Product> findAll() {
        return products;
    }

    // 상품 등록
    public Product save(Product product) {
        // 새 상품에 아이디 자동 부여
        product.setProductId(++sequence);
        products.add(product);
        return product;
    }

    // 상품 하나 조회 (id로)
    public Product findById(int productId) {
        return products.stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst()
                .orElse(null);
    }

    // 상품 수정
    public Product update(int productId, Product updateParam) {
        Product product = findById(productId);
        if (product != null) {
            product.setName(updateParam.getName());
            product.setLimitDate(updateParam.getLimitDate());
        }
        return product;
    }

    // 상품 삭제
    public boolean delete(int productId) {
        return products.removeIf(p -> p.getProductId() == productId);
    }
}
