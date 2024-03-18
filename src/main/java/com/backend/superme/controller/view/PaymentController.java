package com.backend.superme.controller.view;


import com.backend.superme.dto.view.PaymentInfoDto;
import com.backend.superme.entity.view.Order;
import com.backend.superme.service.view.OrderService;
import com.backend.superme.service.view.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/payment")
public class PaymentController {

    private OrderService orderService;
    private PaymentService paymentService;

    @Autowired
    public PaymentController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<String> pay(@PathVariable Long orderId, @RequestBody PaymentInfoDto payment) {
        Order order = orderService.getOrder(orderId);
        boolean ret = paymentService.processPayment(order, payment);
        if (!ret) {
            return ResponseEntity.badRequest().body("Payment failed");
        }
        return ResponseEntity.ok("Payment successful");
    }
}
