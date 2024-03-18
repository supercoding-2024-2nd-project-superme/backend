package com.backend.superme.repository.view;

import com.backend.superme.entity.view.PayMoneyTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayMoneyTransactionLogRepository extends JpaRepository<PayMoneyTransactionLog, Long> {

}