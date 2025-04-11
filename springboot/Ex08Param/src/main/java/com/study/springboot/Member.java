package com.study.springboot;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// @Value의 용도
// 1. springframework의 @Value
//  1) application의 설정값을 주입하는 용도
// 2. lmbok의 @Value
//  1) final, private, getter, equals, toString  등이 생성됨

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Value("변사또") // 기본값 설정
    private String username;
    @Value("3333")
    private String password;
}
