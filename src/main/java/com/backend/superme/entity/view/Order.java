package com.backend.superme.entity.view;

import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id", unique=true)
    private UserEntity user;


    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "order_item")
    private Item orderItem; //주문한 상품

    @Column(name="total_price", nullable = false, precision = 10, scale =2)
    private BigDecimal totalPrice;

    private String deliveryAddress; //배송 주소

    private Date orderDate; //주문일

    private String status; //주문 상태
}