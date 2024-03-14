package com.backend.superme.dto.adminItemDto;

import com.backend.superme.entity.view.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemRegistrationDTO {
    //물품등록 DTO



    @NotBlank(message = "상품 이름 입력")
    private String item_name; // 상품 이름

    @NotBlank(message = "상품 설명 입력")
    private String description; // 설명

    @NotBlank(message = "사이즈 입력")
    private String sizeOption; //S,M,L

//    @NotNull(message = "수량 상태 선택")
//    private Item.StockStatus stock_status; // 수량 상태, 재고있음, 없음, 백오더

    @NotNull(message = "가격을 입력")
    private BigDecimal price; // 가격


    private Category category; //카테고리

    private String mainImg; //이미지 URL 또는 파일경로

    private LocalDateTime registration_date; //등록날짜
    private LocalDateTime termination_date; //판매 종료일자


}
