package com.backend.superme.dto.view;

import com.backend.superme.constant.order.OrderStatus;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.Item;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
