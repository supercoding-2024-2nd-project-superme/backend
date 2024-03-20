package com.backend.superme.dto.adminItemDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.List;

public record UpdateItemRequest(

        @Schema(description = "상품 이름", example = "노트북")
        String itemName,


        @Schema(description = "카테고리 id", example = "1")
        Long categoryId,


        @Schema(description = "상품 가격", example = "99.99")
        BigDecimal price,


        @Schema(description = "상품 상세 설명", example = "가볍고")
        String description,


        @Schema(description = "상품 옵션", example = "color, size, stock")
        List<Option> optionValue


) {
    public record Option(
            String key,
            String value) {
    }
}
