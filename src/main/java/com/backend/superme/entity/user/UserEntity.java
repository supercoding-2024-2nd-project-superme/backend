package com.backend.superme.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Date;

@Entity
@Getter
@NoArgsConstructor
//@Table(name = "account")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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
    @Column(unique = true)
    private String kakaoLogin;
    @Column(unique = true)
    private String naverLogin;
    @CurrentTimestamp
    private Date signupDate;

    public enum Gender {
        FEMALE, MALE, OTHER
    }
}


