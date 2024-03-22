package com.backend.superme.service.user;

import com.backend.superme.config.user.JwtTokenProvider;
import com.backend.superme.config.user.UserPrincipal;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        Map<String, Object> attributes = user.getAttributes();
        String email = (String) attributes.get("email");
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseGet(() -> createUser(attributes));
        // 새로운 사용자 정보를 데이터베이스에 저장합니다.
        userRepository.save(userEntity); // 주석 해제

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                user.getAttributes(),
                "name");
    }

    private UserEntity createUser(Map<String, Object> attributes) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail((String) attributes.get("email"));
        userEntity.setUsername((String) attributes.get("name"));
        userEntity.setProfile((String) attributes.get("picture"));
        // 여기서 userEntity를 바로 반환하기 전에 저장할 필요는 없습니다.
        // userRepository.save(userEntity) 호출은 loadUser 메소드에서 처리됩니다.
        return userEntity;
    }
}


