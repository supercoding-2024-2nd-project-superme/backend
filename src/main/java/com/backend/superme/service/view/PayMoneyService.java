package com.backend.superme.service.view;

import com.backend.superme.dto.view.PaymentInfoDto;
import com.backend.superme.entity.view.Order;
import org.springframework.stereotype.Service;

@Service
public class PayMoneyService {
    // 페이머니 결제 처리 메서드
    public boolean processPayment(Order order, PaymentInfoDto paymentInfoDto) {
        // 페이머니 결제 처리 로직 구현
        // 단순히 성공 여부를 반환하도록 가정
        return true;
    }
}
