package com.backend.superme.service.view;

import com.backend.superme.dto.view.ItemDetailDto;
import com.backend.superme.dto.view.ItemDto;
import com.backend.superme.entity.view.Category;
import com.backend.superme.entity.view.Item;
import com.backend.superme.repository.adminRepository.CategoryRepository;
import com.backend.superme.repository.view.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.backend.superme.entity.view.QItem.item;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void processOrder(Long itemId, int quantity) {
        // 주문 처리 로직 구현
    }

    //모든 상품 조회
    public List<ItemDto> findAllItems() {
        return itemRepository.findAll().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    }

    // 특정 ID를 가진 아이템 상세정보 조회
    public ItemDetailDto findItemDetailById(Long id) {
        return itemRepository.findById(id)
                .map(entity-> new ItemDetailDto(entity.getId(), entity.getName(), entity.getPrice(), entity.getDescription()))
                .orElseThrow(()-> new EntityNotFoundException("Item not found"));
    }

    // 이름 또는 카테고리를 기준으로 상품 검색
    public List<ItemDto> searchItems(String name, String _category) {
        Category category = categoryRepository.findByName(_category);

        if (name != null && category != null) {
            return itemRepository.findByNameContainingOrCategory(name, category).stream()
                    .map(Item::toDto)
                    .collect(Collectors.toList());
        } else if (name != null) {
            return itemRepository.findByNameContaining(name).stream()
                    .map(Item::toDto)
                    .collect(Collectors.toList());
        } else if (category != null) {
            return itemRepository.findByCategory(category).stream()
                    .map(Item::toDto)
                    .collect(Collectors.toList());
        } else {
            return findAllItems(); // 아무 조건도 없으면 모든 아이템 반환
        }
    }

    // 카테고리디테일 페이지 아이템 조회
    public List<ItemDto> getItemsByCategory(String _category) {
        // 카테고리 이름으로 카테고리를 찾음
        Category category = categoryRepository.findByName(_category);
        // 카테고리로 필터링된 아이템들을 가져오는 로직
        List<Item> items = itemRepository.findByCategory(category);
        // ItemDto 리스트로 변환하여 반환
        return items.stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    }

    public List<ItemDto> sortItemsByNameAscending() {
        return itemRepository.findAllByOrderByNameAsc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    };

    // 이름으로 정렬된 상품 조회 (Z to A)
    public List<ItemDto> sortItemsByNameDescending() {
        return itemRepository.findAllByOrderByNameDesc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    };

    // 가격 높은 순으로 정렬된 상품 조회
    public List<ItemDto> sortItemsByPriceAscending() {
        return itemRepository.findAllByOrderByPriceAsc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    };

    // 가격 낮은 순으로 정렬된 상품 조회
    public List<ItemDto> sortItemsByPriceDescending() {
        return itemRepository.findAllByOrderByPriceDesc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    };

    // 판매 날짜 최신순으로 정렬된 상품 조회
    public List<ItemDto> sortItemsByRegistrationDateAscending() {
        return itemRepository.findByOrderByRegistrationDateAsc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    };

    // 판매 날짜 오래된 순으로 정렬된 상품 조회
    public List<ItemDto> sortItemsByRegistrationDateDescending() {
        return itemRepository.findByOrderByRegistrationDateDesc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    };

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