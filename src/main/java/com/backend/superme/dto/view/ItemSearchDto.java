package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {
    private String keyword;//검색 키워드
    private String type;//검색 유형 ("아이템명" 또는 "카테고리"

}
