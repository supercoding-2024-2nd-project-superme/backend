package com.backend.superme.service.user;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenBlacklistService {
    private final Set<String> blacklistedTokens = new HashSet<>();

    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}


//TokenBlacklistService 클래스는 JWT 토큰의 블랙리스트 관리를 위한 서비스로,
// 로그아웃한 사용자의 토큰을 무효화하는 기능을 제공합니다.
// 이 서비스는 Spring의 @Service 어노테이션으로 선언되어 있으며, Spring 컨테이너에 의해 관리됩니다.
// 클래스 내부에서는 blacklistedTokens라는 HashSet을 사용하여 블랙리스트에 포함된 토큰들을 관리합니다.
//
//핵심 기능
//blacklistToken(String token)
//이 메소드는 주어진 토큰을 블랙리스트에 추가합니다.
// 사용자가 로그아웃을 요청할 때, 해당 사용자의 현재 토큰을 이 리스트에 추가함으로써 더 이상 해당 토큰으로 시스템에 접근할 수 없게 합니다.

//isTokenBlacklisted(String token)
//주어진 토큰이 블랙리스트에 포함되어 있는지 확인합니다.
// 이 메소드는 시스템에 대한 모든 요청에서 호출될 수 있으며, 요청에 사용된 토큰이 블랙리스트에 있는 경우 요청을 거부합니다.

//사용 예
//TokenBlacklistService는 로그아웃 처리 로직에서 주로 사용됩니다.
// 사용자가 로그아웃을 요청하면,
// 해당 요청을 처리하는 컨트롤러 또는 인터셉터에서 blacklistToken 메소드를 호출하여 사용자의 토큰을 블랙리스트에 추가합니다.
// 이후, 시스템에 대한 모든 요청은 인증 필터에서 isTokenBlacklisted 메소드를 통해 해당 토큰이 블랙리스트에 포함되어 있는지 확인하고,
// 포함된 경우 요청을 거부합니다.
//
//장점과 한계
//장점: TokenBlacklistService를 사용함으로써, 로그아웃한 사용자의 토큰을 즉시 무효화할 수 있으며, 이를 통해 보안성을 강화할 수 있습니다.
//한계: 현재 구현에서 blacklistedTokens는 메모리 내의 HashSet으로 관리되기 때문에,
// 애플리케이션이 재시작되면 블랙리스트 정보가 사라집니다. 또한, 대규모 시스템에서는 메모리 사용량이 문제가 될 수 있으며,
// 분산 시스템 환경에서는 모든 인스턴스 간에 블랙리스트 상태를 동기화하는 추가적인 메커니즘이 필요합니다.
// 이러한 한계를 극복하기 위해, 블랙리스트를 외부 저장소(예: Redis)에 저장하는 방법을 고려할 수 있습니다.
