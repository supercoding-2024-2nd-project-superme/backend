package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemUpdateDto {
    private Long id;
    private int quantity;
    //장바구니 내역 수정시 사용되는 데이터
}
