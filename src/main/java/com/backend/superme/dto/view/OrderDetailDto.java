package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDetailDto {
    private Long orderId; // 주문 ID
    private String status; // 주문 상태
    private Date orderDate; // 주문 일자
    private BigDecimal totalAmount; // 총 주문 가격
    private String deliveryAddress; // 배송 주소
    private List<ItemDto> orderedItem; // 주문된 상품 정보



}
