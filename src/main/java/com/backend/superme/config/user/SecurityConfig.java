package com.backend.superme.config.user;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((auth) -> auth

                                .requestMatchers("/", "/h2-console/**", "/login", "/signup/**", "/index").permitAll()
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                                .anyRequest().authenticated()
                );


        http
                .csrf((auth) -> auth.disable());

        http.headers((headers) -> headers
                .frameOptions().disable()
        );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}