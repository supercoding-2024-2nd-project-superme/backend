package com.backend.superme.config.user;

import com.backend.superme.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
// Lombok 라이브러리의 어노테이션입니다.
// @Data는 모든 필드에 대한 getter, setter, toString, equals, hashCode 메소드를 자동으로 생성합니다.
// @AllArgsConstructor는 모든 필드 값을 파라미터로 받는 생성자를 자동으로 생성합니다.
public class UserPrincipal implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    // 사용자 식별자, 이메일, 비밀번호 및 권한 목록을 필드로 가집니다.
    // 여기서 권한(GrantedAuthority)은 사용자가 수행할 수 있는 보안 관련 작업(예: 역할)을 나타냅니다.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
        // 사용자에게 부여된 권한 목록을 반환합니다.
    }

    @Override
    public String getPassword() {
        return password;
        // 사용자의 비밀번호를 반환합니다.
    }

    @Override
    public String getUsername() {
        return email;
        // 사용자의 이름(여기서는 이메일)을 반환합니다.
        // Spring Security에서는 일반적으로 'username'을 사용자 식별 정보로 사용합니다.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // 계정의 만료 여부를 반환합니다. 여기서는 항상 true를 반환하여 계정이 만료되지 않음을 나타냅니다.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // 계정의 잠김 여부를 반환합니다. 여기서는 항상 true를 반환하여 계정이 잠기지 않음을 나타냅니다.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        // 사용자의 인증 정보(비밀번호)가 만료되었는지 여부를 반환합니다. 여기서는 항상 true를 반환합니다.
    }

    @Override
    public boolean isEnabled() {
        return true;
        // 사용자 계정이 활성화(사용 가능) 상태인지 여부를 반환합니다. 여기서는 항상 true를 반환합니다.
    }

    public static UserPrincipal create(UserEntity userEntity) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(userEntity.getRole())
                // UserEntity의 role 필드를 이용하여 사용자의 권한을 생성합니다.
                // 이 예제에서는 간단히 하나의 권한만을 부여하고 있습니다.
        );

        return new UserPrincipal(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                authorities
                // UserEntity로부터 UserPrincipal 객체를 생성하여 반환하는 팩토리 메서드입니다.
                // 사용자의 ID, 이메일, 비밀번호 및 권한 목록을 UserPrincipal 생성자에 전달합니다.
        );
    }
}

// UserPrincipal 클래스는 Spring Security의 UserDetails 인터페이스를 구현하여,
// 애플리케이션의 사용자 인증과 권한 부여 과정에서 사용자 정보를 Spring Security에 제공하는 역할을 합니다.
// 클래스 내의 각 메소드와 주요 구성 요소에 대한 설명은 다음과 같습니다.

// UserPrincipal 클래스는 UserDetails 인터페이스를 구현함으로써,
// Spring Security에서 사용자의 인증 정보(이름, 비밀번호, 권한 등)를 관리하는 데 필요한 메소드를 제공합니다.
// 또한, create 메서드를 통해 UserEntity 인스턴스로부터 UserPrincipal 객체를 생성할 수 있으므로,
// 데이터베이스에서 조회한 사용자 정보를 Spring Security에서 사용할 수 있는 형태로 변환하는 데 활용됩니다.
// 이를 통해, 사용자 인증 및 권한 부여 과정을 효율적으로 처리할 수 있습니다.