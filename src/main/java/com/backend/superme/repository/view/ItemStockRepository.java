package com.backend.superme.repository.view;

import com.backend.superme.entity.view.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemStockRepository extends JpaRepository<ItemStock, Long> {
    // 상품 ID를 기반으로 ItemStock 인스턴스를 조회하는 메서드
    ItemStock findByItemId(Long itemId);
}
