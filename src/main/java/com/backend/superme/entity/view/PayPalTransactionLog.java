package com.backend.superme.entity.view;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static com.backend.superme.entity.view.QOrder.order;

@Entity
@Getter
@Setter
public class PayPalTransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;


    // 페이팔 결제 트랜잭션 로그 정보 및 관련 필드들


}
