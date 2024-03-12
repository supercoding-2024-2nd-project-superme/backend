package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemDetailDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;


}
