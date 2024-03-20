package com.backend.superme.service.view;

import com.backend.superme.constant.base.OrderStatus;

import com.backend.superme.constant.base.PaymentMethod;
import com.backend.superme.dto.view.OrderCreateDto;
import com.backend.superme.entity.view.*;
import com.backend.superme.repository.view.*;
import com.backend.superme.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final UserService userService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    private final PaymentService paymentService;

    @Autowired
    public OrderService(UserService userService, CartRepository cartRepository, CartItemRepository cartItemRepository,
                        OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                        ItemRepository itemRepository, PaymentService paymentService) {
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
        this.paymentService = paymentService;
    }

    // 주문 생성 로직
    public Order createOrder(OrderCreateDto orderCreateDto) {
        // 주문 생성 및 데이터 매핑
        BigDecimal totalPrice = calculateTotalPrice(orderCreateDto); // 주문에 필요한 총 금액 계산

        if (orderCreateDto.getPaymentMethod().equals(PaymentMethod.PAYPAL)) {
            // PayPal 결제 방법 처리
            if (orderCreateDto.getPaypalStatus().equals("order.status === \"COMPLETED\"")) {
                // 클라이언트가 완료 통신을 받은 경우 주문을 정상 처리
                return createOrderAndSave(orderCreateDto, totalPrice);
            } else {
                // 클라이언트가 완료 통신을 받지 않은 경우 주문 불가 처리
                throw new RuntimeException("PayPal 결제가 완료되지 않았습니다.");
            }
        } else if (orderCreateDto.getPaymentMethod().equals(PaymentMethod.PAYMONEY)) {
            // 페이머니 결제 방법 처리
            if (orderCreateDto.getPaymoneyStatus().equals("None")) {
                // 페이머니를 사용하지 않은 경우 주문을 정상 처리
                return createOrderAndSave(orderCreateDto, totalPrice);
            } else if (orderCreateDto.getPaymoneyStatus().startsWith("NNN")) {
                // 페이머니를 사용한 경우 주문을 정상 처리
                return processWalletPayment(orderCreateDto, totalPrice);
            } else {
                // 유효하지 않은 페이머니 상태인 경우 주문 불가 처리
                throw new RuntimeException("유효하지 않은 페이머니 상태입니다.");
            }
        }

        // 기타 결제 방법에 대한 처리
        return null;
    }

    private Order processWalletPayment(OrderCreateDto orderCreateDto, BigDecimal totalPrice) {
        Long userId = orderCreateDto.getUserId(); // 사용자 ID
        BigDecimal userBalance = userService.getUserBalance(userId); // 사용자의 페이머니 잔액 조회

        if (userBalance.compareTo(totalPrice) < 0) {
            throw new RuntimeException("페이머니 잔액이 부족합니다."); // 잔액 부족 시 예외 처리
        }

        // 페이머니 잔액 차감
        BigDecimal remainingBalance = userBalance.subtract(totalPrice);
        userService.updateUserBalance(userId, remainingBalance);

        // 주문 생성 및 저장
        return createOrderAndSave(orderCreateDto, totalPrice);
    }

    private Order createOrderAndSave(OrderCreateDto orderCreateDto, BigDecimal totalPrice) {
        // 주문 생성 및 저장
        Item orderItem = itemRepository.findById(orderCreateDto.getItemIds().get(0)).orElse(null);

        Order order = Order.builder()
                .totalPrice(totalPrice)
                .deliveryAddress(orderCreateDto.getDeliveryAddress()) // 클라이언트에서 입력된 주소 값으로 변경
                .orderDate(new Date())
                .status(OrderStatus.WAITING)
                .paymentMethod(orderCreateDto.getPaymentMethod())
                .build();

        return orderRepository.save(order);
    }

    private BigDecimal calculateTotalPrice(OrderCreateDto orderCreateDto) {
        // 주문에 필요한 총 금액 계산 로직
        List<Long> itemIds = orderCreateDto.getItemIds();
        List<Item> items = itemRepository.findAllById(itemIds);
        return items.stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("주문이 존재하지 않습니다."));
    }
}