package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayPalPaymentInfoDto {
    // 페이팔 결제에 필요한 정보들
    private String paymentMethod; // 결제 수단 (페이머니, 페이팔 등)
    // 기타 결제 정보 필드들...
}
