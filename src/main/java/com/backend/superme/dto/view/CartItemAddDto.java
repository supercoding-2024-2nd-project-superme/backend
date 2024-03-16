package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemAddDto {
    private Long itemId; // 추가할 상품의 ID
    private int quantity; // 추가할 상품의 수량
    private String color; // 추가할 상품의 색상
    private String size; // 추가할 상품의 사이즈
}
