package com.backend.superme.dto.user;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.sql.Date;

@Getter
@Setter
@ToString
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
    private String kakaoLogin;
    private String naverLogin;
    private Date signupDate;


}
