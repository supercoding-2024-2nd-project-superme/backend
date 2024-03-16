package com.backend.superme.entity.view;

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

    /*
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id", unique=true
    private User user;
    */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item")
    private Item orderItem;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    private String deliveryAddress;

    private Date orderDate;

    private String status;
}
