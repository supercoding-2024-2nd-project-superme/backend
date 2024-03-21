package com.backend.superme.service.view;

import com.backend.superme.dto.view.ItemDetailDto;
import com.backend.superme.dto.view.ItemDto;
import com.backend.superme.entity.view.Item;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public interface ItemService {

    void processOrder(Long itemId, int quantity);
    List<ItemDto> findAllItems();

    Item saveItem(Item item);
    void deleteItem(Long id);

    ItemDetailDto findItemDetailById(Long id);

    List<ItemDto> searchItems(String name, String _category);

    List<ItemDto> getItemsByCategory(String _category);
    List<Item> findItemsByStockId(Long stockId);

    // 이름으로 정렬된 상품 조회 (A to Z)
    List<ItemDto> sortItemsByNameAscending();

    // 이름으로 정렬된 상품 조회 (Z to A)
    List<ItemDto> sortItemsByNameDescending();

    // 가격 높은 순으로 정렬된 상품 조회
    List<ItemDto> sortItemsByPriceAscending();

    // 가격 낮은 순으로 정렬된 상품 조회
    List<ItemDto> sortItemsByPriceDescending();

    // 판매 날짜 최신순으로 정렬된 상품 조회
    List<ItemDto> sortItemsByRegistrationDateAscending();

    // 판매 날짜 오래된 순으로 정렬된 상품 조회
    List<ItemDto> sortItemsByRegistrationDateDescending();

}

