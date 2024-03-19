package com.backend.superme.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ItemDetailDto {
    //상세물품 조회시 사용되는 데이터
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;


}
