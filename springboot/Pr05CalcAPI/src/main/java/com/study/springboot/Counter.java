package com.study.springboot;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Counter {
    private int count;
}
