package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentInfoDto {
    private String paymentMethod; // 결제 수단
    private BigDecimal amount; // 결제 금액
    private String cardNumber; // 카드 번호
    private String expiryDate; // 만료일
    private String cvv; // CVV 등 기타 결제 수단 관련 정보

}