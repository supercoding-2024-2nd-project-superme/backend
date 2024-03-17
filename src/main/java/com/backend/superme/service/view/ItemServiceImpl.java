package com.backend.superme.service.view;

import com.backend.superme.dto.view.ItemDetailDto;
import com.backend.superme.dto.view.ItemDto;
import com.backend.superme.entity.view.Item;
import com.backend.superme.repository.view.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.backend.superme.entity.view.QItem.item;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public void processOrder(Long itemId, int quantity) {
        // 주문 처리 로직 구현
    }

    //모든 상품 조회
    public List<ItemDto> findAllItems() {
        return itemRepository.findAll().stream()
                .map(entity ->  new ItemDto(entity.getId(), entity.getName(), entity.getPrice()))
                .collect(Collectors.toList());
    }

    // 특정 ID를 가진 아이템 상세정보 조회
    public ItemDetailDto findItemDetailById(Long id) {
        return itemRepository.findById(id)
                .map(entity-> new ItemDetailDto(entity.getId(), entity.getName(), entity.getPrice(), entity.getDescription()))
                .orElseThrow(()-> new EntityNotFoundException("Item not found"));
    }

    // 이름 또는 카테고리를 기준으로 상품 검색
    public List<ItemDto> searchItems(String name, String category) {
        if (name != null && category != null) {
            return itemRepository.findByItemNameContainingOrCategoryContaining(name, category).stream()
                    .map(entity -> new ItemDto(entity.getId(), entity.getName(), entity.getPrice()))
                    .collect(Collectors.toList());
        } else if (name != null) {
            return itemRepository.findByItemNameContaining(name).stream()
                    .map(entity -> new ItemDto(entity.getId(), entity.getName(), entity.getPrice()))
                    .collect(Collectors.toList());
        } else if (category != null) {
            return itemRepository.findByCategory(category).stream()
                    .map(entity -> new ItemDto(entity.getId(), entity.getName(), entity.getPrice()))
                    .collect(Collectors.toList());
        } else {
            return findAllItems(); // 아무 조건도 없으면 모든 아이템 반환
        }
    }

    // 카테고리디테일 페이지 아이템 조회
    public List<ItemDto> getItemsByCategory(String category) {
        // 카테고리로 필터링된 아이템들을 가져오는 로직
        List<Item> items = itemRepository.findByCategory(category);
        // ItemDto 리스트로 변환하여 반환
        return items.stream()
                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice()))
                .collect(Collectors.toList());
    }





    ///기존


    @Override
    public Optional<Item> findItemById(Long id) {
        // ID로 아이템을 찾아 반환. Optional<Item>을 반환하도록 변경
        return itemRepository.findById(id);
    }
    @Override
    public Item saveItem(Item item) {
        // 아이템 저장 로직
        return itemRepository.save(item);
    }
    @Override
    public void deleteItem(Long id) {
        // 아이템 삭제 로직
        itemRepository.deleteById(id);
    }


}
