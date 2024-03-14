package com.backend.superme.repository.view;


import com.backend.superme.constant.item.StockStatus;

import com.backend.superme.entity.view.Item;
import com.backend.superme.entity.view.ItemStock;
import com.backend.superme.constant.item.StockStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 모든 상품 조회
    List<Item> findAll();

    // 상품명으로 상품 검색
    List<Item> findByItemNameContaining(String itemName);

    // 색상 옵션으로 상품 검색
    List<Item> findByColorOption(String colorOption);

    // 사이즈 옵션으로 상품 검색
    List<Item> findBySizeOption(String sizeOption);

    // 재고 상태로 상품 검색
    List<Item> findByStockStatus(StockStatus stockStatus);

    // 가격 범위로 상품 검색
    List<Item> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // 등록 날짜 기반으로 최신 상품 조회
    List<Item> findByOrderByRegistrationDateDesc();

    // 상품의 유효 기간이 남은 상품만 조회
    List<Item> findByTerminationDateAfter(Date currentDate);

    // Todo: 유저(판매자)별 상품 조회
    // List<Item> findBySeller(User seller);

    // Todo: 카테고리별 상품 조회
    // List<Item> findByCategory(Category category);
}
