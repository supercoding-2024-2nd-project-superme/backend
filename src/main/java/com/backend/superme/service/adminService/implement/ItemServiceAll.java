package com.backend.superme.service.adminService.implement;

import com.backend.superme.dto.adminItemDto.category.ItemFullInfoDTO;
import com.backend.superme.entity.ItemImgEntity.AdminItemImageEntity;
import com.backend.superme.entity.view.Item;
import com.backend.superme.repository.adminRepository.AdminItemImageRepository;
import com.backend.superme.repository.view.ItemRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ItemServiceAll {

    private final ItemRepository itemRepository;
    private final AdminItemImageRepository adminItemImageRepository;

    public List<ItemFullInfoDTO> getAllItemsFullInfo() {
        List<Item> items = itemRepository.findAll();  // find all items
        List<ItemFullInfoDTO> itemFullInfoDTOS = new ArrayList<>();

        for (Item item : items) {
            ItemFullInfoDTO itemFullInfoDTO = new ItemFullInfoDTO();
            itemFullInfoDTO.setItemId(item.getId());
            itemFullInfoDTO.setItemName(item.getName());
            itemFullInfoDTO.setPrice(item.getPrice());
            itemFullInfoDTO.setCategoryName(item.getCategory().getName());

            List<AdminItemImageEntity> adminItemImageEntities = adminItemImageRepository.findByItem(item);
            if (!adminItemImageEntities.isEmpty()) {
                itemFullInfoDTO.setImgUrls(Collections.singletonList(adminItemImageEntities.get(0).getImageUrl()));
            }

            itemFullInfoDTOS.add(itemFullInfoDTO);
        }

        return itemFullInfoDTOS;
    }
}