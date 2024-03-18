package com.backend.superme.entity.view;

import com.backend.superme.constant.item.StockStatus;
import jakarta.persistence.*;
import com.backend.superme.entity.view.Item;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="item_stock")
public class ItemStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item; // 'Long itemId' 대신 'Item item'으로 변경

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "stock_qty")
    private int stockQty;

    public ItemStock(Item item, String color, String size, int stockQty) {
        this.item = item;
        this.color = color;
        this.size = size;
        this.stockQty = stockQty;
    }

    //재고 수량 확인 및 업데이트, 부족할 경우 예외 발생
    public void updateQuantityWithCheck(int soldQuantity) throws Exception {
        if (this.stockQty < soldQuantity) {
            throw new Exception("재고 수량이 부족합니다.");
        }
        this.stockQty -= soldQuantity;
    }

    // 주문 완료 시 수량 업데이트, 예외 처리 없음
    public void updateQuantityWithoutCheck(int soldQuantity) {
        this.stockQty -= soldQuantity;
     }
    public void restoreQuantity(int quantity) {
        this.stockQty += quantity;
    }

    // 새로운 메서드: 재고 상태 반환
    public StockStatus getStockStatus() {
        if (this.stockQty > 0) {
            return StockStatus.IN_STOCK;
        } else {
            return StockStatus.OUT_OF_STOCK;
        }
    }

    // 재고 상태 메시지 반환 메서드
    public String getStockStatusMessage() {
        switch (getStockStatus()) {
            case IN_STOCK:
                return "In stock, ready to ship";
            case OUT_OF_STOCK:
                return "Out of Stock";
            case BACK_ORDER:
                return "Low stock,"+ stockQty + "items left";
            default:
                return "알 수 없는 상태";
        }
    }
    //재고 지우기
    public void removeStock(int quantity) throws Exception {
        if (this.stockQty < quantity) {
            throw new Exception("재고 수량이 부족합니다.");
        }
        this.stockQty -= quantity;
    }

}
