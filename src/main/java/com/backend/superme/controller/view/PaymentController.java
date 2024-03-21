package com.backend.superme.controller.view;


import com.backend.superme.dto.view.PaymentInfoDto;
import com.backend.superme.entity.view.Order;
import com.backend.superme.service.view.OrderService;
import com.backend.superme.service.view.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "결제 API")
@RestController("/payment")
@RequestMapping("/payments")
@Tag(name = "결제", description = "결제 API입니다..")
public class PaymentController {

    private OrderService orderService;
    private PaymentService paymentService;

    @Autowired
    public PaymentController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @PostMapping("/pay/{orderId}")
    @Operation(summary = "결제 확인입니다.", description = "결제 확인을 하는 api 입니다.")
    public ResponseEntity<String> pay(@PathVariable Long orderId, @RequestBody PaymentInfoDto payment) {
        Order order = orderService.getOrder(orderId);
        boolean ret = paymentService.processPayment(order, payment);
        if (!ret) {
            return ResponseEntity.badRequest().body("Payment failed");
        }
        return ResponseEntity.ok("Payment successful");
    }
}
