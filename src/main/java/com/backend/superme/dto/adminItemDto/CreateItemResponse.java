package com.backend.superme.dto.adminItemDto;


import java.util.List;


public record CreateItemResponse(


//        @Schema(description = "상품 id", example = "1")
        Long itemId,

//        @Schema(description = "상품 이름", example = "노트북")
        // 상품 이름

//        @Schema(description = "카테고리 id", example = "1")

//        @Schema(description = "상품 가격", example = "879000")
        String price,

        //    @Schema(description = "상품 옵션", example = "{색상: WHITE}")
        String optionValue,

        //    @Schema(description = "상품 상세 설명", example = "가볍고 화질이 선명해요.")
        String description,


//        @Schema(description = "상품 이미지 id 리스트", example = "[1, 2, 3]")
        String itemDescription, List<Long> imgId,

//        @Schema(description = "상품 이미지 url 리스트", example = "[\"https://dachaebucket.s3.ap-northeast-2.amazonaws.com/123.jpg\"]")
        List<String> imgUrls


) {
    public record Option(
            String key,
            String value
    ) {
    }
}
