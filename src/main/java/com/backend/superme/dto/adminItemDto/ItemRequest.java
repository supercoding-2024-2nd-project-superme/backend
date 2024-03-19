package com.backend.superme.dto.adminItemDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public record ItemRequest(
        Long categoryId,
        String itemName,
        BigDecimal price,
        String description,
        List<ItemStockRequest> itemStocks
) {

    public record ItemStockRequest(
            String color,
            String size,
            int stockQty
    ) {
    }
}

