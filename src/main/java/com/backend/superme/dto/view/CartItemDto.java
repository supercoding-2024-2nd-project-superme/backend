package com.backend.superme.dto.view;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CartItemDto {
    //CartItemDto 폼으로부터 받는 CartItem 상세 정보
    private Long id;
    private Long userId; // 사용자 ID
    //아이템 정보(공통)
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;
    private String itemName;
    private List<String> getItemImgUrls; //아이템 특정 이미지(색깔별은 이미지 추후 구현)
    //아이템별 컬러,사이즈, 주문 수량 정보
    private String color; // 추가할 상품의 색상
    private String size; // 추가할 상품의 사이즈
    @Min(value = 1,message = "최소 1개 이상 담아주세요")
    private int count;//구매할 상품의 수량

    private BigDecimal price; //추가상품의 가격

    private Date addedAt; //추가한 시점

    private List<String> itemImgUrls;


}


