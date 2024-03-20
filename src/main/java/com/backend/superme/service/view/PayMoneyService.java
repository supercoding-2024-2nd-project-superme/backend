package com.backend.superme.service.view;

import com.backend.superme.dto.view.PaymentInfoDto;
import com.backend.superme.entity.view.Order;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.PayMoneyAccount;
import com.backend.superme.entity.view.PayMoneyTransactionLog;
import com.backend.superme.repository.view.PayMoneyAccountRepository;
import com.backend.superme.repository.view.PayMoneyTransactionLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import java.math.BigDecimal;

@Service
public class PayMoneyService {
    // 페이머니 결제 처리 메서드
    private PayMoneyAccountRepository payMoneyAccountRepository;
    private PayMoneyTransactionLogRepository payMoneyTransactionLogRepository;

    public PayMoneyService(PayMoneyAccountRepository payMoneyAccountRepository, PayMoneyTransactionLogRepository payMoneyTransactionLogRepository) {
        this.payMoneyAccountRepository = payMoneyAccountRepository;
        this.payMoneyTransactionLogRepository = payMoneyTransactionLogRepository;
    }

    public boolean processPayment(Order order, PaymentInfoDto paymentInfoDto) {
        // 1. paymentInfoDto의 카드번호 정보를 이용하여 payMoneyAccount 정보를 조회
        PayMoneyAccount myAccount = this.payMoneyAccountRepository.findByCardNumber(paymentInfoDto.getCardNumber());

        // 2. payMoneyAccount의 잔액을 조회
        BigDecimal balance = myAccount.getBalance();

        // 3. payMoneyAccount의 잔액이 결제 금액보다 크거나 같은지 확인합니다.
        if (balance.compareTo(order.getTotalPrice()) < 0) {
            return false;
        }

        // 4. 결제 금액을 payMoneyAccount의 잔액에서 차감합니다.
        myAccount.setBalance(balance.subtract(order.getTotalPrice()));

        // 5. 결제 대금을 받을 상점의 payMoneyAccount는 null로 처리합니다.
        // 원래는 상점의 payMoneyAccount 정보와 User 정보를 조회하여 처리해야 하지만, Order 객체에 해당 정보가 없으므로 null로 처리합니다.
        PayMoneyAccount serviceAccount = null;
        UserEntity serviceUser = null;

        // 6. 결제 트랜잭션을 저장 (payMoneyTransactionLogRepository를 사용합니다.)
        PayMoneyTransactionLog payMoneyTransactionLog = PayMoneyTransactionLog.builder()
                .sender(new UserEntity(order.getUser().getId())) // UserEntity 객체를 생성하여 넘겨줍니다.
                .senderPayMoneyAccount(myAccount) // PayMoneyAccount 객체를 전달합니다.
                .amount(order.getTotalPrice())
                .build();
        this.payMoneyTransactionLogRepository.save(payMoneyTransactionLog);

        // 6. 성공 여부를 반환합니다.
        return true;
    }
}
