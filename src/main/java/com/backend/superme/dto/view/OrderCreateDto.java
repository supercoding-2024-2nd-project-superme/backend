package com.backend.superme.dto.view;


import com.backend.superme.constant.base.OrderStatus;
import com.backend.superme.constant.base.PaymentMethod;
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
   private Long userId; // 사용자 ID
   private List<Long> itemIds; // 주문할 상품 ID 목록
   private BigDecimal totalPrice;
   private BigDecimal totalAmount;
   private String deliveryAddress;
   private PaymentMethod paymentMethod; // 결제 방법 추가
   private String paypalStatus; // PayPal 결제 상태
   private String paymoneyStatus; // 페이머니 결제 상태
   private Date orderDate = new Date(); // 주문일
   private OrderStatus status = OrderStatus.CONFIRMED; // 주문 상태



}
