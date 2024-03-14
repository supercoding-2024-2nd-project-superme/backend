package com.backend.superme.service.view;

import com.backend.superme.entity.view.Item;
import com.backend.superme.entity.view.ItemStock;
import com.backend.superme.repository.view.ItemRepository;
import com.backend.superme.repository.view.ItemStockRepository;
import com.backend.superme.service.view.ItemServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public interface ItemService {
    void processOrder(Long itemId, int quantity);
    // 모든 상품 조회
    List<Item> findAllItems();
    // 특정 상품 조회
    Item findItemById(Long id);
    Item saveItem(Item item);
    // void deleteItem(Long id); // 추후 구현 예정




}

