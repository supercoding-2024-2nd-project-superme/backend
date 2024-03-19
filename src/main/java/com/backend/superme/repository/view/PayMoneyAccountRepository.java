package com.backend.superme.repository.view;

import com.backend.superme.entity.view.PayMoneyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMoneyAccountRepository extends JpaRepository<PayMoneyAccount, Long> {
    // 페이머니 관련 기능 추가 필요
    // 카드번호로 조회
    public PayMoneyAccount findByCardNumber(String cardNumber);
}
