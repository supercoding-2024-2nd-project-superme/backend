package com.backend.superme.entity.view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id") //확인 필요
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")//확인 필요
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")//확인 필요
    private Order order;

    private BigDecimal orderPrice;
    private int count;
    private Date regTime;
    private Date updateTime;


    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); // 인스턴스를 사용하여 비정적 메서드 호출
        orderItem.setCount(count);// 인스턴스를 사용하여 비정적 메서드 호출
        orderItem.setOrderPrice(item.getPrice());

        item.removeStock(count);
        return orderItem;
    }
}
