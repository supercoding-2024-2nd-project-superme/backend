package com.backend.superme.service.view;

import com.backend.superme.entity.view.Item;
import com.backend.superme.repository.view.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void processOrder(Long itemId, int quantity) {
        // 구현 내용...
    }

    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }// Todo

    @Override
    public Item findItemById(Long id) {
        // 구현 내용...
        return null;
    }

    @Override
    public Item saveItem(Item item) {
        // 구현 내용...
        return null;
    }

     public void deleteItem(Long id) {
      //메소드 구현 Todo
     }

    // Todo 삭제 추후 구현 예정...


    public Item itemView(Long id) {

        return null;
    }




}
