package com.backend.superme.dto.view;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemListDto {
    private List<ItemDetailDto> items;
}
