package com.backend.superme.entity.view;

import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Date;

// 페이머니 계좌
@Entity
@Getter
@Setter
@Table(name = "pay_money_account")
public class PayMoneyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 발급 은행
    @Column(nullable = false)
    private String bank;

    // 유저 아이디
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // 잔액
    @Column(nullable = false)
    private BigDecimal balance; //달러화

    // 카드 정보
    @Column(nullable = false)
    private String cardNumber;

    // cvv
    @Column(nullable = false)
    private String cvv;

    // 생성 시간
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // 만료 일자
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String expiryDate;

    // 활성화 여부
    @Column(nullable = false)
    private String active;
}
