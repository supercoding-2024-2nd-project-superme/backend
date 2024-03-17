package com.backend.superme.repository.view;

import com.backend.superme.entity.view.PayPalTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayPalRepository extends JpaRepository<PayPalTransactionLog, Long> {
    // 페이팔 관련 기능 추가 필요
}
