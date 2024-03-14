package com.backend.superme.entity.view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.backend.superme.entity.view.ItemStock;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    //주문 항목 생성
    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); // 인스턴스를 사용하여 비정적 메서드 호출
        orderItem.setCount(count);// 인스턴스를 사용하여 비정적 메서드 호출
        // 주문 가격 설정
        orderItem.setOrderPrice(item.getPrice());

        // 재고 감소 처리, item.removeStock()이 재고를 감소시키는 메서드라고 가정
        try {
            item.removeStock(count); // 재고 감소 처리
        } catch (Exception e) {
            // 예외 처리 로직, 예를 들어 로그를 남기고 사용자 정의 예외를 던질 수 있습니다.
            // 예외 발생 시 특정 액션을 취할 수 있도록 설계할 수 있습니다.
            throw new RuntimeException("재고 수량이 부족하여 주문을 처리할 수 없습니다.", e);
        }

        return orderItem;
    }
}
