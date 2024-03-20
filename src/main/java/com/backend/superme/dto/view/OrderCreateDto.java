package com.backend.superme.dto.view;


import com.backend.superme.constant.base.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {
   private Long userId;
   private List<Long> itemIds; // 주문한 상품
   private BigDecimal totalPrice;
   private String deliveryAddress;
   private Date orderDate = new Date(); // 주문일
   private OrderStatus status = OrderStatus.CONFIRMED; // 주문 상태
}
