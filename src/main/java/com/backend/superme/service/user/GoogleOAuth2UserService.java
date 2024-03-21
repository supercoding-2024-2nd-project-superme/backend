package com.backend.superme.service.user;

import com.backend.superme.config.user.JwtTokenProvider;
import com.backend.superme.constant.user.RoleEnum;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.repository.user.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class GoogleOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public GoogleOAuth2UserService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");


        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        UserEntity userEntity = new UserEntity();
        if (userEntityOptional.isEmpty()) {
            // 새로운 사용자 저장

            userEntity.setEmail(email);
            userEntity.setUsername(name);
            // 역할 설정
            userEntity.setRole(RoleEnum.USER);
            userRepository.save(userEntity);
        }

        return oAuth2User;
    }
}