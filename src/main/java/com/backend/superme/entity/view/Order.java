package com.backend.superme.entity.view;


import com.backend.superme.constant.base.DeliveryStatus;
import com.backend.superme.constant.base.OrderStatus;
import com.backend.superme.constant.base.PaymentMethod;
import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id", unique=true)
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems; // 주문 항목 리스트 추가

    @Column(name="total_price", nullable = false, precision = 10, scale =2)
    private BigDecimal totalPrice;

    private String deliveryAddress; //배송 주소

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod; // 결제 방법

    private Date orderDate; //주문일

    private OrderStatus status = OrderStatus.CONFIRMED; //주문 상태

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

}