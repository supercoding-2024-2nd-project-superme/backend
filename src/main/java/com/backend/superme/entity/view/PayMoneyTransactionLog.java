package com.backend.superme.entity.view;

import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paymoney_transaction_log")
public class PayMoneyTransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 보낸 사람 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    // 보낸 페이머니 계좌
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_paymoney_id")
    private PayMoneyAccount senderPayMoneyAccount;

    // 받는 사람 ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    // 받는 페이머니 계좌
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_paymoney_id")
    private PayMoneyAccount receiverPayMoneyAccount;

    // 보낸 금액
    @Column(nullable = false)
    private Long amount;

    // 보낸 시간
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime = new Date();

    // 처리 시간
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date processTime = new Date();
}
