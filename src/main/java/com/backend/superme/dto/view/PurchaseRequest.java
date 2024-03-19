package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseRequest {
    private Long itemId;
    private int purchased_qty; //구매 수량

}
