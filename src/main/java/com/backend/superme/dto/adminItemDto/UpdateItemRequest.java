package com.backend.superme.dto.adminItemDto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.List;

public record UpdateItemRequest(
        @NotBlank
//        @Schema(description = "상품 이름", example = "노트북")
//        @Size(min = 2, max = 50)
        String itemName,

//        @NotNull
//        @Schema(description = "카테고리 id", example = "1")
        Long categoryId,

//        @Min(value = 1)
//        @NotNull
//        @Schema(description = "상품 가격", example = "879000")
        BigDecimal price,

        @Nullable
//        @Schema(description = "상품 상세 설명", example = "가볍고 화질이 선명해요.")
        String description,

        @Nullable
//        @Schema(description = "상품 옵션", example = "{색상: WHITE}")
        List<Option> optionValue


) {
    public record Option(
            String key,
            String value) {
    }
}
