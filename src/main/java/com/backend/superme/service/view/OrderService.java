package com.backend.superme.service.view;

import com.backend.superme.dto.view.OrderCreateDto;
import com.backend.superme.entity.view.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.superme.repository.view.OrderRepository;
import com.backend.superme.service.view.PaymentService;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    @Autowired
    public OrderService(OrderRepository orderRepository, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    //장바구니 주문 로직
    public void createOrderFromCart(OrderCreateDto orderCreateDto) {
        // 1. 장바구니에서 상품 정보 가져오기 및 주문 생성
            Order order = createOrder(orderCreateDto);
        // 2. 결제 처리 로직 호출
        boolean paymentSuccess = paymentService.processPayment(order, orderCreateDto.getPaymentInfo());

        // 3. 주문 정보 저장
            if (paymentSuccess){
                finalizeOrder(order);
            }
       // 4. 장바구니 비우기
    }
    // 주문 생성 로직
    private Order createOrder(OrderCreateDto orderCreateDto) {
        // 주문 생성 및 데이터 매핑
        Order order = new Order();
        // 데이터 매핑 및 저장
        return orderRepository.save(order);
    }

    // 주문 완료 처리
    private void finalizeOrder(Order order) {
        // 주문 상태 변경 및 저장
        order.setStatus("주문 완료");
        orderRepository.save(order);
    }
}