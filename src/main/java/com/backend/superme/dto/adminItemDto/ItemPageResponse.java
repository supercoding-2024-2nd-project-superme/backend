package com.backend.superme.dto.adminItemDto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

public record ItemPageResponse(
        @Schema(description = "총 페이지 수", example = "10")
        int totalPage,

        @Schema(description = "총 항목 수", example = "50")
        long totalCount,

        @Schema(description = "현재 페이지 번호", example = "1")
        int pageNumber,

        @Schema(description = "아이템 갯수", example = "16")
        int itemCount,

        @Schema(description = "상품 목록")
        List<ItemList> itemList

) {
    public record ItemList(
            @Schema(description = "상품 id", example = "1")
            Long itemId,

            @Schema(description = "상품 이름", example = "노트북")
            String itemName,

            @Schema(description = "상품 가격", example = "897000")
            BigDecimal price

    ) {
    }
}
