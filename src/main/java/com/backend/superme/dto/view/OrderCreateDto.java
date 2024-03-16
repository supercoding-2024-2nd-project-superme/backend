package com.backend.superme.dto.view;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateDto {
   private Long itemId; // 주문할 상품의 ID
   private int quantity; // 주문 수량
   private PaymentInfoDto paymentInfo; // 결제 정보 필드

   }
