package com.backend.superme.entity.view;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order; //주문 정보

    private BigDecimal orderPrice;
    private int count;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regTime; //주문 항목 생성 시점

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime; //주문 항목 업데이트 시점


    // 주문 항목을 생성하는 정적 메서드
    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = OrderItem.builder()
                .item(item) // 주문 아이템 설정
                .count(count) // 주문 수량 설정
                .orderPrice(item.getPrice()) // 주문 가격
                .regTime(new Date()) //현재 시점으로 등록 시점 설정
                .updateTime(new Date()) //현재 시점으로 업데이트 시점 설정
                .build();

        try {
            item.removeStock(count); // 주문 수량만큼 재고 감소
        } catch (Exception e) {
            throw new RuntimeException("재고 수량이 부족하여 주문을 처리할 수 없습니다.", e);
        }

        return orderItem;
    }

    public void setOrderedQty(int orderedQty) {
        this.count = orderedQty;
    }
}
