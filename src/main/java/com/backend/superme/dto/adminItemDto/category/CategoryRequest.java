package com.backend.superme.dto.adminItemDto.category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "카테고리 요청 DTO")
public record CategoryRequest(
        @Schema(description = "카테고리명", example = "MAN 또는 WOMAN")
        String categoryName
) {
}