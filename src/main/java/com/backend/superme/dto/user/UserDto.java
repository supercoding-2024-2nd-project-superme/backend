package com.backend.superme.dto.user;


import com.backend.superme.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String username;
    private String nickname;
    private String profile;
    private String address;
    private String phone;
    private String gender;
    private String role;
    private String provider;
    private String status;
    private Date signupDate;

    // 이메일을 받아들이는 생성자
    public UserDto(String email) {
        this.email = email;
    }


    public static UserDto fromEntity(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .username(userEntity.getUsername())
                .nickname(userEntity.getNickname())
                .profile(userEntity.getProfile())
                .address(userEntity.getAddress())
                .phone(userEntity.getPhone())
                .gender(userEntity.getGender().toString())
                .role(userEntity.getRole().toString())
                .provider(userEntity.getProvider())
                .status(userEntity.getStatus().toString())
                .signupDate(userEntity.getSignupDate())
                .build();
    }
}
