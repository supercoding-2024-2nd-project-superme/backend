package com.backend.superme.dto.user;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
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
}
