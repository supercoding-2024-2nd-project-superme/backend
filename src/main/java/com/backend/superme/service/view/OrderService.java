package com.backend.superme.service.view;

import com.backend.superme.constant.order.OrderStatus;
import com.backend.superme.dto.view.OrderCreateDto;
import com.backend.superme.entity.view.*;
import com.backend.superme.repository.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.superme.service.view.PaymentService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderService(CartRepository cartRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ItemRepository itemRepository, PaymentService paymentService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
    }

    // 장바구니 주문 로직
    public void createOrderFromCart(Long cartId) {
        // 1. 카트 검색
        Cart cart = cartRepository.findById(cartId).orElse(null);
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        // 2. 카트 기반으로 주문 생성
        BigDecimal totalPrice = cartItems.stream()
                .map(cartItem -> cartItem.getItem().getPrice().multiply(BigDecimal.valueOf(cartItem.getOrdered_qty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .totalPrice(totalPrice)
                .deliveryAddress("서울시 강남구")
                .orderDate(new Date())
                .status(OrderStatus.WAITING)
                .build();

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            Item item = cartItem.getItem();
            return OrderItem.builder()
                    .item(item)
                    .order(order)
                    .count(cartItem.getOrdered_qty())
                    .regTime(new Date())
                    .updateTime(new Date())
                    .build();
        }).toList();

        // 3. 주문 정보 저장
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        // 4. 카트 삭제
        cartItemRepository.deleteAll(cartItems);
        cartRepository.delete(cart);
    }

    // 주문 조회 로직
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    // 주문 생성 로직
    public Order createOrder(OrderCreateDto orderCreateDto) {
        // 주문 생성 및 데이터 매핑
        Item orderItem = itemRepository.findById(orderCreateDto.getItemIds().get(0)).orElse(null);

        Order order = Order.builder()
                .totalPrice(BigDecimal.valueOf(0))
                .deliveryAddress("서울시 강남구")
                .orderDate(new Date())
                .status(OrderStatus.WAITING)
                .build();
        // 데이터 매핑 및 저장
        return orderRepository.save(order);
    }

    // 주문 완료 처리
    public void finalizeOrder(Order order) {
        // 주문 상태 변경 및 저장
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }
}