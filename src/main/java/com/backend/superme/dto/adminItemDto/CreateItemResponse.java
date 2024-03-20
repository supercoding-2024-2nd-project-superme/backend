package com.backend.superme.dto.adminItemDto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


public record CreateItemResponse(


        @Schema(description = "상품 id", example = "1")
        Long itemId,

        @Schema(description = "상품 가격", example = "99.99")
        String price,

        @Schema(description = "상품 옵션", example = "{색상: WHITE}")
        String categoryName, String optionValue,

        @Schema(description = "상품 상세 설명", example = "가볍고 화질이 선명해요.")
        String description,


        @Schema(description = "상품 설명", example = "이건 남자옷입니까?")
        String itemDescription,

        @Schema(description = "이미지 아이디", example = "1")
        List<Long> imgId,

        @Schema(description = "상품 이미지 url 리스트", example = "[\"https://dachaebucket.s3.ap-northeast-2.amazonaws.com/123.jpg\"]")
        List<String> imgUrls


) {
    public record Option(
            String key,
            String value
    ) {
    }
}
