package com.backend.superme.entity.user;

import com.backend.superme.constant.user.GenderEnum;
import com.backend.superme.constant.user.RoleEnum;
import com.backend.superme.constant.user.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.USER;

    private String provider;
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.ACTIVE;
    @CreationTimestamp
    private Date signupDate;

    private BigDecimal balance;
    public UserEntity(Long id) {
        this.id = id;
    }

}


