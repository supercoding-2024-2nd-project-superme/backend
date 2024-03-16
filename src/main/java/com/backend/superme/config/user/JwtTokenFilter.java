package com.backend.superme.config.user;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    // JwtTokenProvider 인스턴스를 주입받아 JWT 관련 작업(토큰 해석, 유효성 검증 등)을 수행합니다.

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        // HTTP 요청에서 JWT 토큰을 추출합니다. 토큰은 보통 'Authorization' 헤더에 'Bearer [토큰]' 형식으로 포함됩니다.


        if (token != null && jwtTokenProvider.validateJwtToken(token)) {
            // 추출한 토큰이 null이 아니며 유효한 경우의 처리를 합니다.
            // 유효한 토큰인 경우, 추가적인 인증 처리나 사용자 정보 설정 등을 수행할 수 있습니다.
            // 이 예제에서는 단순히 응답 헤더에 'Authorization' 값을 추가하는 예시를 보여줍니다.
            response.addHeader("Authorization", "Bearer " + token);
        }
        // 모든 요청에 대해 이 필터를 실행한 후, 다음 필터로 요청을 전달합니다.
        // 필터 체인의 다음 필터가 없다면 실제 요청을 처리하는 컨트롤러로 요청이 전달됩니다.
        filterChain.doFilter(request, response);
    }
}

//JwtTokenFilter 클래스는 OncePerRequestFilter를 상속받아, 스프링 시큐리티 필터 체인에서 단 한 번만 실행되는 커스텀 필터입니다.
// 이 필터의 주 목적은 들어오는 모든 HTTP 요청에서 JWT 토큰을 확인하고, 토큰의 유효성을 검사하는 것입니다.
// 필터는 유효한 토큰이 발견되면 추가적인 인증 처리를 할 수 있는 기회를 제공합니다.

//주요 기능 및 역할:
//
//토큰 추출 및 검증: 들어오는 요청에서 JWT 토큰을 추출하고, JwtTokenProvider를 사용하여 그 유효성을 검사합니다.
//응답에 토큰 추가: 유효한 토큰을 확인한 경우, 선택적으로 헤더에 토큰을 다시 추가하여 응답할 수 있습니다.
//하지만 이 작업은 일반적으로 필요하지 않으며, 여기서는 설명을 위해 포함된 것입니다.
//보안 강화: 유효한 JWT 토큰을 확인하여 애플리케이션의 보안을 강화합니다.
//유효하지 않거나 만료된 토큰을 가진 요청은 추가 처리 없이 필터 체인을 통과시켜 최종적으로 접근을 제한할 수 있습니다.
//이 커스텀 필터를 스프링 시큐리티 필터 체인에 등록하기 위해서는
// SecurityConfig에서 addFilterBefore 또는 addFilterAfter 메서드를 사용하여 적절한 위치에 필터를 추가해야 합니다.




