package com.study.pr06vmapi;

import java.time.LocalDate;

public class Product {
    private int productId;         // 상품번호
    private String name;           // 상품이름
    private LocalDate limitDate;   // 유통기한

    // 기본 생성자
    public Product() {}

    // 생성자
    public Product(int productId, String name, LocalDate limitDate) {
        this.productId = productId;
        this.name = name;
        this.limitDate = limitDate;
    }

    // Getter/Setter
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }
    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }
}

