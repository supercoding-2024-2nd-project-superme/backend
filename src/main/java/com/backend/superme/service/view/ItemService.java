package com.backend.superme.service.view;

import com.backend.superme.entity.view.Item;
import com.backend.superme.repository.view.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public interface ItemService {


    // 모든 상품 조회
    List<Item> findAllItems();

    Item itemView(Long id);

    //특정 상품 아이디 조회
    Item findItemById(Long id);
    Item saveItem(Item item);

   /* Todo 삭제 추후
    void deleteItem(Long id);
    */


}
