package com.backend.superme.service.user;

import com.backend.superme.config.user.UserPrincipal;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserTokenService  { //implements UserDetailsService


//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByEmail(email)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found with email : " + email)
//                );
//
//        return UserPrincipal.create(userEntity);
//    }
}


// CustomUserDetailsService 클래스는 UserDetailsService 인터페이스의 구현체로,
// Spring Security에서 사용자의 인증 정보를 로드하는 데 사용됩니다.
// 이 클래스는 사용자 인증 과정에서 주어진 사용자명(이 경우 이메일)을 바탕으로 사용자 정보를 데이터베이스에서 조회하고,
// 이를 바탕으로 UserDetails 객체를 생성하여 반환하는 역할을 합니다.

// 핵심 구성 요소 및 기능
// @Autowired private UserRepository userRepository;
// UserRepository는 사용자 정보를 조회하기 위해 사용되는 JPA 리포지토리입니다. 이 리포지토리를 통해 데이터베이스에서 사용자 정보를 조회합니다.

//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//    loadUserByUsername 메소드는 주어진 사용자명(여기서는 이메일)으로 사용자 정보를 조회하고,
//    해당 정보를 바탕으로 UserDetails 객체를 생성하여 반환합니다.
//    사용자 정보 조회에 실패하면 UsernameNotFoundException을 발생시킵니다.

//    UserEntity userEntity = userRepository.findByEmail(email)
//    findByEmail 메소드를 통해 주어진 이메일에 해당하는 사용자 정보(UserEntity)를 데이터베이스에서 조회합니다.

//    .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
//    조회된 사용자 정보가 없을 경우 UsernameNotFoundException 예외를 발생시켜, 사용자 정보가 없음을 알립니다.

//    return UserPrincipal.create(userEntity);
//    조회된 UserEntity 객체를 바탕으로 UserPrincipal 객체를 생성합니다.
//    UserPrincipal는 Spring Security에서 사용자의 인증 정보를 담는 UserDetails 인터페이스의 구현체입니다.
//    UserPrincipal.create 메소드는 UserEntity로부터 필요한 정보를 추출하여 UserPrincipal 객체를 생성하는 팩토리 메소드입니다.

//     역할 및 중요성
//    CustomUserDetailsService는 Spring Security의 인증 과정에서 중요한 역할을 합니다.
//    사용자가 시스템에 로그인을 시도할 때, 입력한 이메일과 비밀번호를 바탕으로 사용자를 인증합니다.
//    이 클래스를 통해, 애플리케이션은 사용자의 이메일을 사용하여 데이터베이스에서 사용자 정보를 조회하고,
//    조회된 정보를 바탕으로 사용자의 인증 및 권한 부여 과정을 진행할 수 있습니다.
//    이 구현체를 사용함으로써, 개발자는 Spring Security의 인증 메커니즘을 자신의 애플리케이션에 맞게 커스터마이징할 수 있으며,
//    데이터베이스의 사용자 정보를 기반으로 안전하고 유연한 인증 과정을 구현할 수 있습니다.