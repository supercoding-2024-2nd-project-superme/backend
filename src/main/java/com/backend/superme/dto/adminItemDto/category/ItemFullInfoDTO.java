package com.backend.superme.dto.adminItemDto.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemFullInfoDTO {
    private long itemId;
    private String itemName;
    private BigDecimal price;
    private String categoryName;
    private List<String> imgUrls;

    // getters and setters
}
