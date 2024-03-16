package com.backend.superme.dto.adminItemDto;

import com.backend.superme.entity.view.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record ItemRequest(

        Long categoryId,

        @NotBlank(message = "상품 이름 입력")
        String itemName, // 상품 이름
        @NotNull(message = "가격을 입력")
        BigDecimal price, // 가격
        @NotBlank(message = "상품 설명 입력")
        String description, // 설명

//        @NotBlank(message = "사이즈 입력")
        String sizeOption, // S, M, L 등
        String colorOption, // S, M, L 등

        @Nullable
//        @Schema(description = "상품 옵션", example = "{색상: WHITE}")
        List<Option> optionValue



) {

        public record Option (
                String key,
                String value) {
        }
}