package com.backend.superme.entity.view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="item_id", nullable = false)
    private Long itemId;

    @Column(name="cart_id", nullable = false)
    private Long cartId;

    @Column(name="ordered_qty", nullable = false)
    private int ordered_qty;

    @Column(name="ordered_color")
    private String orderedColor;

    @Column(name="ordered_color")
    private String orderedSize;

    @Column(name="ordered_size")
    private Date addedAt;

}
