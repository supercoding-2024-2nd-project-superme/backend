package com.backend.superme.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemSearchDto {
    private String keyword;
    private Long categoryId;

    // 키워드로 검색할 때
    public ItemSearchDto(String keyword) {
        this.keyword = keyword;
    }

    // 카테고리 ID로 검색할 때 사용되는 생성자
    public ItemSearchDto(Long categoryId) {
        this.categoryId = categoryId;
    }

    // 특정 물품명 검색 메서드
    public static ItemSearchDto searchByKeyword(String keyword) {
        return new ItemSearchDto(keyword);
    }

    // 특정 카테고리 검색 메서드
    public static ItemSearchDto searchByCategory(Long categoryId) {
        return new ItemSearchDto(categoryId);
    }
}
