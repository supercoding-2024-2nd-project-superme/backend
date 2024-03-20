package com.backend.superme.dto.adminItemDto.category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리 응답 DTO")
public record CategoryResponse(

        @Schema(description = "카테고리 ID", example = "1")
        Long categoryId,
@Schema(description = "카테고리명", example = "MAN")
        String categoryName
) {
}
