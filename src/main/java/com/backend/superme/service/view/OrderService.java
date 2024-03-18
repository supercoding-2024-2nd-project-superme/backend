package com.backend.superme.service.view;

import com.backend.superme.dto.view.OrderCreateDto;
import com.backend.superme.entity.view.Order;
import com.backend.superme.repository.view.OrderRepository;
import com.backend.superme.repository.view.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;
    private final CartRepository cartRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, PaymentService paymentService, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
        this.cartRepository = cartRepository;
    }

    //장바구니 주문 로직
    public void createOrderFromCart(OrderCreateDto orderCreateDto) {
        // 1. 장바구니에서 상품 정보 가져오기 및 주문 생성
        Order order = createOrder(orderCreateDto);
        // 2. 결제 처리 로직 호출
        boolean paymentSuccess = paymentService.processPayment(order, orderCreateDto.getPaymentInfo());
        // 3. 결제가 성공하면 주문 완료 처리 및 장바구니 비우기
        if (paymentSuccess) {
            finalizeOrder(order);
            clearCart(); // 장바구니 비우기
        }
    }

    // 주문 생성 메서드
    private Order createOrder(OrderCreateDto orderCreateDto) {
        // 여기에 주문을 생성하는 로직
        Order order = new Order();
        // 주문 정보 설정
        return order;
    }

    // 주문 완료 처리
        private void finalizeOrder (Order order){
            // 주문 상태 변경 및 저장
            order.setStatus("주문 완료");
            orderRepository.save(order);
        }

    private void clearCart() {
        // 장바구니를 비우는 로직 구현
        cartRepository.deleteAll();
    }
}