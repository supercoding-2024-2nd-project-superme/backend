package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDetailDto {
    private Long orderId;
    private List<ItemDetailDto> items;
    private double totalAmount; //Todo 확인 필요

}
