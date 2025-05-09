package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())                       // POST 허용
                .headers(h -> h.frameOptions(f -> f.sameOrigin()))  // iframe 허용
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()  // ★ 반드시 /**   (슬래시+별 두 개)
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.disable());               // 기본 로그인 폼 제거(선택)
        return http.build();
    }
}

