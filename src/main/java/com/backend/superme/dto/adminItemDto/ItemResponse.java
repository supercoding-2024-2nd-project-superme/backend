package com.backend.superme.dto.adminItemDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Schema(description = "상품 상세 조회 응답 DTO")
public record ItemResponse(
        @Schema(description = "상품 id", example = "1")
        Long itemId,
        @Schema(description = "상품 이름", example = "노트북")
        String itemName,
        @Schema(description = "카테고리 id", example = "1")
        Long categoryId,
        @Schema(description = "상품 가격", example = "99.99")
        BigDecimal price,
        @Schema(description = "상품 옵션", example = "size,color,stock")
        List<Option> optionValue,
//        @Schema(description = "상품 상세 설명", example = "가볍고 화질이 선명해요.")
//        String description,

        String description, List<String> imgUrls
) {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Option {
        private String color;
        private String size;
        private int stockQty;
    }
}