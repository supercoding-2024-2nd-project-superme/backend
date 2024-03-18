package com.backend.superme.controller.view;

import com.backend.superme.constant.order.OrderStatus;
import com.backend.superme.dto.view.CartItemDto;
import com.backend.superme.dto.view.OrderCreateDto;
import com.backend.superme.entity.view.Order;
import com.backend.superme.service.view.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
        //장바구니 주문 API
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        // 장바구니 주문 생성(카트 아이템 정보& 사용자 이메일을 이용)
        Order result = orderService.createOrder(orderCreateDto);

        // 생성된 주문 정보를 클라이언트에게 반환
        return ResponseEntity.ok(result);
    }
}
