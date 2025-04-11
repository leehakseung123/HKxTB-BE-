package com.study.pr04CounterAPI;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Counter {
    private int count;
}