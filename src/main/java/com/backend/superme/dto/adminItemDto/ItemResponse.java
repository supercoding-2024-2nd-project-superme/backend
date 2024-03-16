package com.backend.superme.dto.adminItemDto;

import java.math.BigDecimal;
import java.util.List;

//상품 상제조회 응답 DTO
public record ItemResponse(
//        @Schema(description = "상품 id", example = "1")
        Long itemId,

//        @Schema(description = "상품 이름", example = "노트북")
        String itemName,

//        @Schema(description = "카테고리 id", example = "1")
        Long categoryId,

//        @Schema(description = "상품 가격", example = "879000")
        BigDecimal price,

//        @Schema(description = "상품 옵션", example = "{색상: WHITE}")
        List<Option> optionValue,
//        @Schema(description = "상품 이미지 url 리스트", example = "[\"https://dachaebucket.s3.ap-northeast-2.amazonaws.com/123.jpg\"]")
        List<String> imgUrls


) {
    public record Option(
            String key,
            String value
    ) {
    }
}
