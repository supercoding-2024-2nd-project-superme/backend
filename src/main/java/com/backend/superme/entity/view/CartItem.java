package com.backend.superme.entity.view;

import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="item_id", nullable = false)
    private Item item; // Item 엔티티 참조

    @ManyToOne
    @JoinColumn(name="cart_id", nullable = false)
    private Cart cart; // Cart 엔티티 참조

    @Column(name="ordered_qty", nullable = false)
    private int orderedQty;

    @Column(name="ordered_color")
    private String orderedColor;

    @Column(name="ordered_size")
    private String orderedSize;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="added_at")
    private Date addedAt;

    @PrePersist
    protected void onCreate() {
        addedAt = new Date(); // 엔티티가 생성되기 전에 현재 날짜와 시간으로 초기화
    }
}
