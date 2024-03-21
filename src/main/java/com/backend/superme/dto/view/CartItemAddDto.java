package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemAddDto {
    private Long itemId; // 추가할 상품의 ID
    private String color; // 추가할 상품의 색상
    private String size; // 추가할 상품의 사이즈
    private int count; // 추가할 상품의 수량
    private BigDecimal price; //추가상품의 가격
    //이미지
}
