package com.backend.superme.dto.adminItemDto;

import com.backend.superme.constant.item.StockStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record SellerItemsResponse(
        @Schema(description = "총 페이지 수", example = "10")
        int totalPage,

        @Schema(description = "총 항목 수", example = "50")
        int totalCount,

        @Schema(description = "현재 페이지 번호", example = "1")
        int pageNumber,

        @Schema(description = "한 페이지당 크기", example = "5")
        int currentPageSize,

        @Schema(description = "판매자가 판매한 상품 리스트")
        List<sellerItem> sellerItemList


) {
    public record sellerItem(
            @Schema(description = "상품 id", example = "1")
            Long itemId,

            @Schema(description = "상품 이름", example = "옷")
            String itemName,

            @Schema(description = "상품 가격", example = "99.99")
            int price,

            @Schema(description = "상품 수량", example = "2000")
            int count,

//            @Schema(description = "상품 판매 상태", example = "판매 중단")
            StockStatus itemState
    ) {

    }
}



