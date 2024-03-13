package com.backend.superme.entity.view;

import jakarta.persistence.*;
import com.backend.superme.entity.view.Item;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item; // 'Long itemId' 대신 'Item item'으로 변경

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "stock_qty")
    private int stockQty;


}
