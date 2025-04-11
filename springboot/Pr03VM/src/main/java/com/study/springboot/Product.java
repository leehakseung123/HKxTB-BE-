package com.study.springboot;

import java.time.LocalDate;

public class Product {
    private String name;
    private int price;
    private LocalDate limitDate;

    public Product() {}

    public Product(String name, int price, LocalDate limitDate) {
        this.name = name;
        this.price = price;
        this.limitDate = limitDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }
    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }
}
