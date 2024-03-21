package com.backend.superme.config.global;

import com.backend.superme.config.user.JwtTokenProvider;
import com.backend.superme.dto.user.UserDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.repository.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


// 컨트롤러에서 사용 예시
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    private final UserInfoRetriever userInfoRetriever;
//
//    public UserController(UserInfoRetriever userInfoRetriever) {
//        this.userInfoRetriever = userInfoRetriever;
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<UserDto> getUserInfo(@RequestHeader("Authorization") String token) {
//        String accessToken = token.replace("Bearer ", "");
//        UserDto userDto = userInfoRetriever.getUserInfo(accessToken);
//        return ResponseEntity.ok(userDto);
//    }
//}

@Component
public class UserInfoRetriever {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public UserInfoRetriever(JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    public UserDto getUserInfo(String token) {
        // 1. 토큰 유효성 검사
        if (!jwtTokenProvider.validateJwtToken(token)) {
            throw new RuntimeException("Invalid token");
        }

        // 2. 토큰에서 이메일 추출
        String email = jwtTokenProvider.getEmailFromToken(token);

        // 3. 이메일로 사용자 엔티티 조회
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        UserEntity userEntity = userOptional.get();

        // 4. 사용자 엔티티를 DTO로 변환하여 반환
        return UserDto.fromEntity(userEntity);
    }
}

