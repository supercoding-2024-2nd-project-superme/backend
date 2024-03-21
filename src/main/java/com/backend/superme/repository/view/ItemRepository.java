package com.backend.superme.repository.view;


import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.Category;
import com.backend.superme.entity.view.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 모든 상품 조회
    List<Item> findAll();

    //재고 ID를 기반으로 아이템을 조회
    List<Item> findByItemStocks_Id(Long stockId);


    // 상품명 또는 카테고리를 포함하는 상품 검색
    List<Item> findByNameContainingOrCategory(String name, Category category);


    // 상품명으로 상품 검색
    List<Item> findByNameContaining(String name);

    // 카테고리별로 검색
    List<Item> findByCategory(Category category);

    //정렬관련 사항
    // 이름으로 정렬된 상품 조회 (A to Z)
    List<Item> findAllByOrderByNameAsc();

    // 이름으로 정렬된 상품 조회 (Z to A)
    List<Item> findAllByOrderByNameDesc();

    // 가격 높은 순으로 정렬된 상품 조회
    List<Item> findAllByOrderByPriceDesc();

    // 가격 낮은 순으로 정렬된 상품 조회
    List<Item> findAllByOrderByPriceAsc();

    // 판매 날짜 최신순으로 정렬된 상품 조회
    List<Item> findByOrderByRegistrationDateDesc();

    // 판매 날짜 오래된 순으로 정렬된 상품 조회
    List<Item> findByOrderByRegistrationDateAsc();
}