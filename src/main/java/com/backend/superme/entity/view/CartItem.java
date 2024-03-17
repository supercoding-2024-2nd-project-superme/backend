package com.backend.superme.entity.view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static com.querydsl.core.types.dsl.Wildcard.count;


@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_id", nullable = false)
    private Item item; // Item 엔티티 참조

    @ManyToOne
    @JoinColumn(name="cart_id", nullable = false)
    private Cart cart; // Cart 엔티티 참조

    @Column(name="ordered_qty", nullable = false)
    private int ordered_qty;

    @Column(name="ordered_color")
    private String orderedColor;

    @Column(name="ordered_size")
    private String orderedSize;

    @Column(name="added_at")
    private Date addedAt;


    public CartItem(){

    }
}
