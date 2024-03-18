package com.backend.superme.entity.view;

import com.backend.superme.constant.order.OrderStatus;
import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.backend.superme.entity.view.QOrderItem.orderItem;

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

    @Column(name="total_price", nullable = false, precision = 10, scale =2)
    private BigDecimal totalPrice;

    private String deliveryAddress; //배송 주소

    private Date orderDate; //주문일

    private OrderStatus status = OrderStatus.CONFIRMED; //주문 상태
}