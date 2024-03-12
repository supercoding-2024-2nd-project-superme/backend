package com.backend.superme.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account")
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String username;
    private String nickname;
    private String profile;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender = Gender.OTHER;
    private String role;
    private String kakaoLogin;
    private String naverLogin;
    private Date signupDate;

    public enum Gender {
        FEMALE, MALE, OTHER
    }
}


