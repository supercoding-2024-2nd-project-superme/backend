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
public class UserPrincipal implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // UserDetails 인터페이스의 메소드 구현

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // User 엔티티로부터 UserPrincipal 객체를 생성하는 팩토리 메서드
    public static UserPrincipal create(UserEntity userEntity) {
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(userEntity.getRole()) // 'role' 필드를 이용해 권한 부여
        );

        return new UserPrincipal(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                authorities
        );
    }
}