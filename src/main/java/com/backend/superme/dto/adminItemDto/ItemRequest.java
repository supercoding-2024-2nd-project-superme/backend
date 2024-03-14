package com.backend.superme.dto.adminItemDto;

import com.backend.superme.entity.view.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    private Long categoryId;

    @NotBlank(message = "상품 이름 입력")
    private String itemName; // 상품 이름

    @NotBlank(message = "상품 설명 입력")
    private String description; // 설명

    @NotBlank(message = "사이즈 입력")
    private String sizeOption; // S, M, L 등

    @NotNull(message = "가격을 입력")
    private BigDecimal price; // 가격

    private Category category; // 카테고리

    private String mainImgUrl; // 이미지 URL

    private LocalDateTime terminationDate; // 판매 종료 일자
}