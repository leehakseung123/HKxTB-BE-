package com.study.pr01counter;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Counter {
    private int count;
}
