package com.backend.superme.service.adminService;

import com.backend.superme.entity.view.Item;
import com.backend.superme.repository.itemRepository.AdminItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final AdminItemRepository itemRepository;

    public List<Item> getRequiredSales() {
        //현재 날짜 이후의 판매 종료 날짜를 가진 아이템 조회
        LocalDateTime currentDate = LocalDateTime.now();
        List<Item> items = itemRepository.findByTerminationDateAfter(currentDate);

        //종료 날짜가 설정되지 않은 아이템도 조회하여 추가
        List<Item> itemAll = itemRepository.findByTerminationDateIsNull();
        items.addAll(itemAll);

        return items;
    }
}
