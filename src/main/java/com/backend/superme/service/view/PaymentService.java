package com.backend.superme.service.view;

import com.backend.superme.dto.view.PaymentInfoDto;
import com.backend.superme.entity.view.Order;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    //PayPal API 서비스 주입
    private final PayPalService payPalService;

    // 페이머니 서비스 주입
    private final PayMoneyService payMoneyService;

    public PaymentService(PayPalService payPalService, PayMoneyService payMoneyService) {
        this.payPalService = payPalService;
        this.payMoneyService = payMoneyService;
    }

    // 결제 처리
    public boolean processPayment(Order order, PaymentInfoDto paymentInfoDto) {
        // 페이팔을 이용한 결제인 경우
        if (paymentInfoDto.getPaymentMethod().equals("PayPal")) {
            // PayPal API를 통한 결제 처리
            boolean paymentSuccess = payPalService.processPayment(order, paymentInfoDto);
            return paymentSuccess;
        }
        // 페이머니를 이용한 결제인 경우
        else if (paymentInfoDto.getPaymentMethod().equals("PayMoney")) {
            // 페이머니 결제 서비스를 통한 결제 처리
            boolean paymentSuccess = payMoneyService.processPayment(order, paymentInfoDto);
            return paymentSuccess;
        } else {
            // 지원하지 않는 결제 수단인 경우
            throw new IllegalArgumentException("Unsupported payment method: " + paymentInfoDto.getPaymentMethod());
        }
    }
}