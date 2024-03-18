package com.backend.superme.dto.view;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDto {
    //CartItemDto 폼으로부터 받는 CartItem 상세 정보
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;
    @Min(value = 1,message = "최소 1개 이상 담아주세요")
    private int count;

}


