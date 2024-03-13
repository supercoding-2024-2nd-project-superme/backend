package com.backend.superme.dto.view;

import com.backend.superme.entity.view.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDto {

    private String itemName;
    private int count;
    private BigDecimal orderPrice;
    private String imgUrl;

    public OrderItemDto(OrderItem orderItem, String imgUrl){
        this.itemName = orderItem.getItem().getItemName();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl=imgUrl;
    }

}

