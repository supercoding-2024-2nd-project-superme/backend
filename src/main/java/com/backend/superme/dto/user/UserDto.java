package com.backend.superme.dto.login;



import lombok.Data;

import java.sql.Date;

@Data
public class LoginDto {

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
