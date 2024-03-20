package com.backend.superme.controller.view;

import com.backend.superme.dto.view.ItemDetailDto;
import com.backend.superme.dto.view.ItemDto;
import com.backend.superme.service.view.ItemService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    // 전체 물품 조회 API
    @GetMapping("/all")
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.findAllItems());
    }

    // 상세 물품 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<ItemDetailDto> getItemDetail(@PathVariable Long id){
        return ResponseEntity.ok(itemService.findItemDetailById(id));
    }


    // 특정 조건에 따른 물품 검색 API
    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> searchItems(@RequestParam(required = false) String name, @RequestParam(required = false) String category){
        return ResponseEntity.ok(itemService.searchItems(name, category));
    }

    // 특정 카테고리에 따른 물품 조회 API
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ItemDto>> getItemsByCategory(@PathVariable String category) {
        // 카테고리로 필터된 데이터를 반환하는 서비스 메서드 호출
        List<ItemDto> itemsByCategory = itemService.getItemsByCategory(category);
        return ResponseEntity.ok(itemsByCategory);
    }


    // 특정 조건에 따라 물품을 정렬하여 조회하는 API
    @GetMapping("/sort")
    public ResponseEntity<List<ItemDto>> sortItems(@RequestParam(required = false) String sortBy){
        // sortBy 파라미터에 따라 정렬 방식을 선택하여 조회
        List<ItemDto> sortedItems;
        switch (sortBy) {
            case "nameAscending":
                sortedItems = itemService.sortItemsByNameAscending();
                break;
            case "nameDescending":
                sortedItems = itemService.sortItemsByNameDescending();
                break;
            case "priceAscending":
                sortedItems = itemService.sortItemsByPriceAscending();
                break;
            case "priceDescending":
                sortedItems = itemService.sortItemsByPriceDescending();
                break;
            case "registrationDateAscending":
                sortedItems = itemService.sortItemsByRegistrationDateAscending();
                break;
            case "registrationDateDescending":
                sortedItems = itemService.sortItemsByRegistrationDateDescending();
                break;
            default:
                // 기본적으로 이름으로 오름차순 정렬
                sortedItems = itemService.sortItemsByNameAscending();
        }
        return ResponseEntity.ok(sortedItems);
    }



}
