package com.backend.superme.repository.view;

import com.backend.superme.entity.view.PayMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMoneyRepository extends JpaRepository<PayMoney, Long> {
    // 페이머니 관련 기능 추가 필요
}
