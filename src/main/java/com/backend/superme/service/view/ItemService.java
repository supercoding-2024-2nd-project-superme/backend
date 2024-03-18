package com.backend.superme.service.view;

import com.backend.superme.dto.view.ItemDetailDto;
import com.backend.superme.dto.view.ItemDto;
import com.backend.superme.entity.view.Item;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public interface ItemService {
    void processOrder(Long itemId, int quantity);
    List<ItemDto> findAllItems();
    Optional<Item> findItemById(Long id);
    Item saveItem(Item item);
    void deleteItem(Long id);
    List<ItemDto> getItemsByCategory(String category);
    ItemDetailDto findItemDetailById(Long id);
    List<ItemDto> searchItems(String name, String category);

    // sortBy 파라미터에 따라 정렬 방식을 선택하여 조회
    List<ItemDto> sortItemsByNameAscending();
    List<ItemDto> sortItemsByNameDescending();
    List<ItemDto> sortItemsByPriceAscending();
    List<ItemDto> sortItemsByPriceDescending();
    List<ItemDto> sortItemsByRegistrationDateAscending();
    List<ItemDto> sortItemsByRegistrationDateDescending();
}

