package com.backend.superme.dto.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    //전체물품 조회시 사용되는 데이터
    private Long id;
    private String name;
    private BigDecimal price;

}
