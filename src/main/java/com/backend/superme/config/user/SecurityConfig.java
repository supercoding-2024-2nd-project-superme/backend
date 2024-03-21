package com.backend.superme.config.user;


import com.backend.superme.service.user.GoogleOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;


@EnableWebSecurity
// Spring Security를 활성화합니다. 이 어노테이션을 사용함으로써,
// Spring Security의 기본 설정이 적용되고, 커스텀 보안 설정을 정의할 수 있게 됩니다.

@RequiredArgsConstructor
// Lombok의 RequiredArgsConstructor 어노테이션을 사용하여,
// 필수적인 생성자를 자동으로 생성합니다. 여기서는 JwtTokenProvider를 주입받는 생성자가 생성됩니다.

@Configuration
// 이 클래스가 Spring의 설정 클래스임을 나타냅니다. Spring의 빈(Bean) 정의와 다른 설정들을 포함할 수 있습니다.

public class SecurityConfig {

    @Autowired
    private final GoogleOAuth2UserService googleOAuth2UserService;

    @Autowired
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    // JWT 토큰 생성 및 검증을 담당하는 JwtTokenProvider 빈을 주입받습니다. 이는 JWT 인증 필터에서 사용됩니다.
    private static final String[] H2_CONSOLE_PATHS = {"/h2-console/**"};
    private static final String[] SWAGGER_PATHS = {
            "/swagger-ui/index.html", "/swagger-ui/index.html/**", "/swagger-ui/**",
            "/v3/api-docs/swagger-config", "/v3/api-docs"
    };
    private static final String[] PERMIT_ALL_PATHS = {
            "/api/admin/categories", "/api/sales/items", "/", "/user/login",
            "/user/signup/**", "/user/index", "/get-current-member", "/api/user",
            //상품 등록,조회
            "/api/items/**", "/api/items/**"

    };
    private static final String[] GOOGLE_OAUTH_PATHS = {"/oauth2/authorization/google"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //정적 리소스 모두허용
                                .requestMatchers(H2_CONSOLE_PATHS).permitAll() //h2
                                .requestMatchers(PERMIT_ALL_PATHS).permitAll() //허용하고 싶은 경로
                                .requestMatchers(SWAGGER_PATHS).permitAll() //swagger 경로
                                .requestMatchers(GOOGLE_OAUTH_PATHS).permitAll()// 구글
                                .requestMatchers("/api/admin/categories", "/api/sales/items", "/", "/user/login",
                                        "/user/signup/**", "/user/index", "/get-current-member", "/api/user",
                                        "/api/items/**", "/api/items/**").permitAll()
                                //상품 등록,조회
                                .anyRequest().authenticated()
                );

        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        // JwtTokenFilter를 UsernamePasswordAuthenticationFilter 전에 추가합니다. 이 필터는 들어오는 요청의 JWT 토큰을 검사하여 인증을 수행합니다.

        http.csrf().disable();
        // Cross-Site Request Forgery (CSRF) 보호 기능을 비활성화합니다. REST API를 구축할 때 일반적으로 필요한 설정입니다.

        http.headers((headers) -> headers.frameOptions().disable());
        // clickjacking 공격을 방지하기 위한 HTTP 헤더인 X-Frame-Options를 비활성화합니다.
        // 특정 페이지가 <frame>, <iframe> 또는 <object> 내에서 렌더링되는 것을 방지하는 데 사용됩니다.

//        http.oauth2Login()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .userInfoEndpoint()
//                .userService(googleOAuth2UserService);


        http.cors().configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://localhost:4040", "https://frontend-lvaynbmcp-yshs-projects-8341d6f4.vercel.app", "*")); // 허용할 출처 설정
            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // 허용할 HTTP 메서드 설정
            config.setAllowedHeaders(Arrays.asList("*")); // 허용할 헤더 설정

            return config;
        });


        return http.build();
        // 최종적으로 HttpSecurity 설정을 기반으로 SecurityFilterChain을 생성합니다.
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // 비밀번호 인코딩에 사용할 PasswordEncoder의 구현체를 빈으로 등록합니다.
        // BCrypt 알고리즘을 사용하는 BCryptPasswordEncoder를 사용합니다.
    }
}


//이 SecurityConfig 클래스는 Spring Security의 설정을 정의합니다.
// 이 설정은 애플리케이션의 보안 관련 설정을 포함하며, 특히 JWT 기반의 인증 메커니즘을 지원하기 위한 구성이 포함되어 있습니다.
// 주요 구성 요소와 역할에 대해 설명하겠습니다.

//이 클래스는 Spring Security 설정의 핵심이며,
// 애플리케이션에 대한 접근 제어, CSRF 보호 비활성화, JWT 인증 필터의 추가 등 보안 관련 여러 중요한 설정을 포함하고 있습니다.
// JWT 인증 필터를 통해, 모든 요청에 대한 인증 과정에서 JWT 토큰의 유효성을 검사하게 되며, 이를 통해 보안성을 강화하고 있습니다.