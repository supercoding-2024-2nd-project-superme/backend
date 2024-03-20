package com.backend.superme.service.view;

import com.backend.superme.dto.view.ItemDetailDto;
import com.backend.superme.dto.view.ItemDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.Category;
import com.backend.superme.entity.view.Item;
import com.backend.superme.entity.view.ItemStock;
import com.backend.superme.repository.adminRepository.CategoryRepository;
import com.backend.superme.repository.view.ItemRepository;
import com.backend.superme.service.user.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;
    private UserService userService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, EntityManager entityManager) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Item> findById(Long userId) {
        // 유저 서비스를 사용하여 유저를 조회
        Optional<UserEntity> user = userService.findById(userId);

        // 유저가 존재하는 경우에만 해당 유저가 소유한 아이템을 조회
        if (user != null) {
            return itemRepository.findByUserId(userId);
        } else {
            // 유저가 존재하지 않는 경우에는 null을 반환하거나 적절한 예외 처리
            return null;
        }
    }

    @Override
    public List<Item> findByUserId(Long userId) {
        // 사용자 ID에 해당하는 상품 목록 조회 로직을 여기에 구현합니다.
        // 예를 들어, 해당 사용자가 등록한 상품들을 조회하는 방식으로 구현할 수 있습니다.
        return itemRepository.findByUserId(userId);
    }


    @Override
    public List<Item> findItemsByStockId(Long itemId) {
        String jpql = "SELECT i FROM Item i JOIN i.itemStocks s WHERE s.item.id = :itemId";
        return entityManager.createQuery(jpql, Item.class)
                .setParameter("itemId", itemId)
                .getResultList();
    }

    @Override
    @Transactional
    public void processOrder(Long itemId, int quantity) {
        // 주문 처리 로직 구현
        // 아이템을 데이터베이스에서 조회
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            // 주문 수량이 재고보다 많은지 확인
            if (item.getItemStocks() != null && !item.getItemStocks().isEmpty()) {
                int totalStock = item.getItemStocks().stream().mapToInt(ItemStock::getStockQty).sum();
                if (totalStock >= quantity) {
                    // 주문 수량만큼 재고 감소
                    for (ItemStock stock : item.getItemStocks()) {
                        int availableStock = stock.getStockQty();
                        if (availableStock >= quantity) {
                            stock.setStockQty(availableStock - quantity);
                            quantity = 0;
                            break;
                        } else {
                            stock.setStockQty(0);
                            quantity -= availableStock;
                        }
                    }
                    itemRepository.save(item);
                    // 여기에 주문 처리 및 기타 로직 추가 가능
                } else {
                    throw new RuntimeException("주문 수량이 재고보다 많습니다.");
                }
            } else {
                throw new RuntimeException("해당 상품의 재고 정보를 찾을 수 없습니다.");
            }
        } else {
            throw new EntityNotFoundException("해당 ID의 상품을 찾을 수 없습니다.");
        }
    }


    //모든 상품 조회
    public List<ItemDto> findAllItems() {
        return itemRepository.findAll().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    }

    //아이템 상세 조회
    public ItemDetailDto findItemDetailById(Long id) {
        return itemRepository.findById(id)
                .map(entity -> new ItemDetailDto(entity.getId(), entity.getName(), entity.getPrice(), entity.getDescription()))
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));
    }

    @Override
    public ItemDetailDto findItemById(Long id) {
        // 아이템 ID로 아이템을 조회
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            // ItemDetailDto에 필요한 정보로 매핑하여 반환
            return new ItemDetailDto(item.getId(), item.getName(), item.getPrice(), item.getDescription());
        } else {
            throw new EntityNotFoundException("Item not found");
        }
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


    // 이름 또는 카테고리를 기준으로 상품 검색
    @Override
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
    @Override
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


    /*정렬*/
    // 이름으로 정렬된 상품 조회 (A to Z)
    @Override
    public List<ItemDto> sortItemsByNameAscending() {
        return itemRepository.findAllByOrderByNameAsc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    }

    ;

    // 이름으로 정렬된 상품 조회 (Z to A)
    @Override
    public List<ItemDto> sortItemsByNameDescending() {
        return itemRepository.findAllByOrderByNameDesc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    }



    // 가격 높은 순으로 정렬된 상품 조회
    @Override
    public List<ItemDto> sortItemsByPriceAscending() {
        return itemRepository.findAllByOrderByPriceAsc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    }


    // 가격 낮은 순으로 정렬된 상품 조회
    @Override
    public List<ItemDto> sortItemsByPriceDescending() {
        return itemRepository.findAllByOrderByPriceDesc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    }

    ;

    // 판매 날짜 최신순으로 정렬된 상품 조회
    @Override
    public List<ItemDto> sortItemsByRegistrationDateAscending() {
        return itemRepository.findByOrderByRegistrationDateAsc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    }

    ;

    // 판매 날짜 오래된 순으로 정렬된 상품 조회
    @Override
    public List<ItemDto> sortItemsByRegistrationDateDescending() {
        return itemRepository.findByOrderByRegistrationDateDesc().stream()
                .map(Item::toDto)
                .collect(Collectors.toList());
    }

    }
