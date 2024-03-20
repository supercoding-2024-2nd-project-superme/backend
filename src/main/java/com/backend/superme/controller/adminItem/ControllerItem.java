package com.backend.superme.controller.adminItem;

import com.backend.superme.dto.adminItemDto.ItemPageResponse;
import com.backend.superme.dto.adminItemDto.ItemResponse;
import com.backend.superme.service.adminService.implement.ImplItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "상품 전체 조회, 상세 조회 api 입니다", description = "전체 사용자가 볼 수 있는 상품 조회 api 입니다")
public class ControllerItem {
    private final ImplItemService itemService;


    //상품 단건 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/items/{itemId}")
    @Operation(summary = "상품 단건 조회" ,description = "상품을 하나씩 조회하는 api 입니다.")
    public ItemResponse getItem(@PathVariable Long itemId) {
        return itemService.getOne(itemId);
    }

//    카테고리 아이템으로 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/items")
    @Operation(summary = "상품 전체 조회", description = "상품을 전체 조회하는 api 입니다")
    public ItemPageResponse getCategoryName(Pageable pageable,
                                        @RequestParam String categoryName) {
        return itemService.categoryNameAll(pageable, categoryName);
    }
}
