package com.backend.superme.controller.adminItem;

import com.backend.superme.dto.adminItemDto.ItemPageResponse;
import com.backend.superme.dto.adminItemDto.ItemResponse;
import com.backend.superme.service.adminService.implement.ImplItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ControllerItem {
    private final ImplItemService itemService;


    //상품 단건 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/items/{itemId}")
    public ItemResponse getItem(@PathVariable Long itemId) {
        return itemService.getOne(itemId);
    }

    //상품 전체 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/items/all")
    public ItemPageResponse getAllItems(Pageable pageable,
                                        @RequestParam Long categoryId) {
        return itemService.getAll(pageable, categoryId);
    }
}
