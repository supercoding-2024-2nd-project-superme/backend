package com.backend.superme.entity.view;

import jakarta.persistence.*;
import lombok.*;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.constant.item.StockStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Setter
@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "main_img")
    private String mainImg;

    @Column(name = "description")
    private String description;

    @Column(name = "color_option")
    private String colorOption;

    @Column(name = "size_option")
    private String sizeOption;

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status")
    private StockStatus stockStatus;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;


    @ManyToOne
    @JoinColumn(name = "seller", referencedColumnName = "id")
    private UserEntity seller;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_stock", referencedColumnName = "id")
    private ItemStock itemStock;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "termination_date")
    private LocalDateTime terminationDate;


    public void removeStock(int count) {
    }
    @Builder
    public Item(String itemName, BigDecimal price, String description, Category category, UserEntity seller,
                String sizeOption, String colorOption) {
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.category = category;
        this.seller = seller;
        this.sizeOption = sizeOption;
        this.colorOption = colorOption;
    }


    public void updateItem(String itemName, BigDecimal itemPrice, String description, Category category, StockStatus stockStatus){
        this.itemName = itemName;
        this.price = itemPrice;
        this.description = description;
        this.category = category;
        this.stockStatus = stockStatus;
    }

}
