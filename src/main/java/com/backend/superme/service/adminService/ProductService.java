package com.backend.superme.service.adminService;

import com.backend.superme.dto.adminItemDto.ItemRequest;
import com.backend.superme.dto.adminItemDto.ItemRegistrationDTO;
import com.backend.superme.entity.view.Item;
import com.backend.superme.repository.itemRepository.AdminItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final AdminItemRepository adminItemRepository;

    public Item registerItem(ItemRegistrationDTO itemDto, LocalDateTime terminationDate) {
        Item item = Item.builder()
                .itemName(itemDto.getItem_name())
                .description(itemDto.getDescription())
                .sizeOption(itemDto.getSizeOption())
                .price(itemDto.getPrice())
                .category(itemDto.getCategory())
                .mainImg(itemDto.getMainImg())
                .registrationDate(itemDto.getRegistration_date())
                .terminationDate(terminationDate)
                .build();
        return adminItemRepository.save(item);
    }

    public void updateItem(ItemRequest dto) {
        // InventoryUpdateDTO를 기반으로 업데이트할 상품을 찾습니다.
        Item itemToUpdate = adminItemRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("해당 ID에 대한 아이템을 찾을 수 없습니다: " + dto.getCategoryId()));
        // 업데이트할 필드들을 InventoryUpdateDTO에서 가져와서 엔티티에 반영합니다.
        itemToUpdate.setItemName(dto.getItemName());
        itemToUpdate.setDescription(dto.getDescription());
        itemToUpdate.setSizeOption(dto.getSizeOption());
        itemToUpdate.setPrice(dto.getPrice());
        itemToUpdate.setCategory(dto.getCategory());
        itemToUpdate.setMainImg(dto.getMainImgUrl());
        itemToUpdate.setTerminationDate(dto.getTerminationDate());

        // 상품을 저장하여 업데이트합니다.
        adminItemRepository.save(itemToUpdate);
    }
}
