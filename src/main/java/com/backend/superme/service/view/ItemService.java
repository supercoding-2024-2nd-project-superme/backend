package com.backend.superme.service.view;

import com.backend.superme.dto.view.ItemDetailDto;
import com.backend.superme.dto.view.ItemDto;
import com.backend.superme.entity.view.Item;
import com.backend.superme.entity.view.ItemStock;
import com.backend.superme.repository.view.ItemRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.List;


@Service
@Transactional
public interface ItemService {
    void processOrder(Long itemId, int quantity);
    List<ItemDto> findAllItems();
    Optional<Item> findItemById(Long id);
    Item saveItem(Item item);
    void deleteItem(Long id);
//
//
//    //모든 상품 조회
//    public List<ItemDto> findAllItems() {
//        return itemRepository.findAll().stream()
//                .map(entity ->  new ItemDto(entity.getId(), entity.getName(), entity.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//    // 특정 ID를 가진 아이템 상세정보 조회
//    public ItemDetailDto findItemDetailById(Long id) {
//        return itemRepository.findById(id)
//                .map(entity-> new ItemDetailDto(entity.getId(), entity.getName(), entity.getPrice(), entity.getDescription()))
//                .orElseThrow(()-> new EntityNotFoundException("Item not found"));
//    }
//
//    // 이름 또는 카테고리를 기준으로 상품 검색
//    public List<ItemDto> searchItems(String name, String category) {
//        if (name != null && category != null) {
//            return itemRepository.findByItemNameContainingOrCategoryContaining(name, category).stream()
//                    .map(entity -> new ItemDto(entity.getId(), entity.getName(), entity.getPrice()))
//                    .collect(Collectors.toList());
//        } else if (name != null) {
//            return itemRepository.findByItemNameContaining(name).stream()
//                    .map(entity -> new ItemDto(entity.getId(), entity.getName(), entity.getPrice()))
//                    .collect(Collectors.toList());
//        } else if (category != null) {
//            return itemRepository.findByCategory(category).stream()
//                    .map(entity -> new ItemDto(entity.getId(), entity.getName(), entity.getPrice()))
//                    .collect(Collectors.toList());
//        } else {
//            return findAllItems(); // 아무 조건도 없으면 모든 아이템 반환
//        }
//    }
//
//    // 카테고리디테일 페이지 아이템 조회
//    public List<ItemDto> getItemsByCategory(String category) {
//        // 카테고리로 필터링된 아이템들을 가져오는 로직
//        List<Item> items = itemRepository.findByCategory(category);
//        // ItemDto 리스트로 변환하여 반환
//        return items.stream()
//                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//
//    //*정렬 관련 사항 *//
//    // 상품명 오름차순으로 정렬하여 조회
//    public List<ItemDto> sortItemsByNameAscending() {
//        return itemRepository.findAll(Sort.by(Sort.Direction.ASC, "name")).stream()
//                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//    // 상품명 내림차순으로 정렬하여 조회
//    public List<ItemDto> sortItemsByNameDescending() {
//        return itemRepository.findAll(Sort.by(Sort.Direction.DESC, "name")).stream()
//                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//    // 가격 오름차순으로 정렬하여 조회
//    public List<ItemDto> sortItemsByPriceAscending() {
//        return itemRepository.findAll(Sort.by(Sort.Direction.ASC, "price")).stream()
//                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//    // 가격 내림차순으로 정렬하여 조회
//    public List<ItemDto> sortItemsByPriceDescending() {
//        return itemRepository.findAll(Sort.by(Sort.Direction.DESC, "price")).stream()
//                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//    // 등록 날짜 최신순으로 정렬하여 조회
//    public List<ItemDto> sortItemsByRegistrationDateDescending() {
//        return itemRepository.findByOrderByRegistrationDateDesc().stream()
//                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//    // 등록 날짜 오래된 순으로 정렬하여 조회
//    public List<ItemDto> sortItemsByRegistrationDateAscending() {
//        // 등록 날짜 오래된 순으로 정렬하려면 내림차순으로 조회 후 뒤집어야 함
//        List<Item> items = itemRepository.findByOrderByRegistrationDateDesc();
//        return items.stream()
//                .map(item -> new ItemDto(item.getId(), item.getName(), item.getPrice()))
//                .collect(Collectors.toList());
//    }
//
//    //저장
//
//    //삭제


}

