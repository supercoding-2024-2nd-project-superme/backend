package com.backend.superme.constant.item;


import lombok.Getter;
import lombok.Setter;

@Getter
public enum StockStatus {
    //재고가 있는 상태
    IN_STOCK,
    //재고가 없는 상태
    OUT_OF_STOCK,
    //주문은 가능하지만, 재고가 없어 배송이 지연되는 상태
    BACK_ORDER,
    //판매 중단
    DISCONTINUED
}
