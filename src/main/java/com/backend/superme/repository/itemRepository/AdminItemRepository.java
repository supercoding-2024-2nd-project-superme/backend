package com.backend.superme.repository.itemRepository;

import com.backend.superme.entity.view.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AdminItemRepository extends JpaRepository<Item, Long> {
    //종료되지 않은 아이템 검색
    List<Item> findByTerminationDateAfter(LocalDateTime currentDate);

    List<Item> findByTerminationDateIsNull();


}
