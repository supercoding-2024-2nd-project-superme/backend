package com.backend.superme.entity.user;

import com.backend.superme.constant.user.GenderEnum;
import com.backend.superme.constant.user.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account")
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
    private GenderEnum gender = GenderEnum.OTHER;
    private String role;
    @Column(unique = true)
    private String kakaoLogin;
    @Column(unique = true)
    private String naverLogin;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @CreationTimestamp
    private Date signupDate;

    private BigDecimal balance;
    public UserEntity(Long id) {
        this.id = id;
    }

}


