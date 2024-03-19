package com.backend.superme.service.view;

import com.backend.superme.dto.view.PaymentInfoDto;
import com.backend.superme.entity.view.Order;
import org.springframework.stereotype.Service;

@Service
public class PayPalService {
    // 페이팔 결제 처리 메서드
    public boolean processPayment(Order order, PaymentInfoDto paymentInfoDto) {
        // 페이팔 결제 처리 로직 구현

        // 페이팔 결제는 항상 성공한다고 가정합니다.

        // 1. paymentInfoDto의 결제정보가 유효한 것으로 간주합니다.

        // 2. 결제성공을 반환합니다.

        return true;

    }
}