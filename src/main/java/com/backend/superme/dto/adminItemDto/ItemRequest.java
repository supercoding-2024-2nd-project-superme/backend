package com.backend.superme.dto.adminItemDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "상품 요청 DTO")
public record ItemRequest(
        @Schema(description = "카테고리 이름", example = "MAN")
        String categoryName,
        @Schema(description = "카테고리 id", example = "1")
        String itemName,
        @Schema(description = "상품 가격", example = "77.77")
        BigDecimal price,
        @Schema(description = "상품 상세 설명", example = "가볍고 깨끗해요.")
        String description,
        @Schema(description = "상품 Option", example = "color, size, stock")
        List<ItemStockRequest> itemStocks
) {

    public record ItemStockRequest(
            String color,
            String size,
            int stockQty
    ) {
    }
}

