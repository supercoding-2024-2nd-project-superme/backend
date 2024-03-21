package com.backend.superme.entity.view;

import com.backend.superme.constant.item.StockStatus;
import com.backend.superme.dto.view.ItemDto;
import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name", nullable = false)
    private String name;

    //  @OneToMany(mappedBy = //Todo)
    @JoinColumn(name = "img_id")
    private String imgId;

    @Column(name = "description")
    private String description;


    @ElementCollection
    @CollectionTable(name = "item_color_options")
    @Column(name = "color_option")
    private List<String> colorOptions = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "item_size_options")
    @Column(name = "size_option")
    private List<String> sizeOptions = new ArrayList<>();

    @OneToMany(mappedBy="item", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ItemStock> itemStocks;



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

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "termination_date")
    private LocalDateTime terminationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;


    @Builder
    public Item(String name, BigDecimal price, String description, Category category, UserEntity seller,
               List<String> colorOptions, List<String> sizeOptions) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.seller = seller;
        this.colorOptions = colorOptions;
        this.sizeOptions = sizeOptions;
    }


    public void updateItem(String name, BigDecimal itemPrice, String description, Category category, StockStatus stockStatus) {
        this.name = name;
        this.price = itemPrice;
        this.description = description;
        this.category = category;
        this.stockStatus = stockStatus;
    }

    public ItemDto toDto(){
        return ItemDto.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .build();
    }

}
