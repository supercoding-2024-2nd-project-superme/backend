package com.backend.superme.entity.view;

import com.backend.superme.constant.item.StockStatus;
import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="item_name", nullable=false)
    private String name;

//  @OneToMany(mappedBy = //Todo)
    @JoinColumn(name="img_id")
    private String imgId;

    @Column(name = "description")
    private String description;


    @ElementCollection
    @CollectionTable(name = "item_color_options")
    @Column(name = "color_option")
    @Builder.Default
    private List<String> colorOption = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "item_size_options")
    @Column(name = "size_option")
    @Builder.Default
    private List<String> sizeOption = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status", nullable=false)
    private StockStatus stockStatus;

    //JS ver
    @OneToMany(mappedBy="item", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ItemStock> itemStocks;
    //YJ ver
//    @ManyToOne
//    @JoinColumn(name = "item_stock", referencedColumnName ="id")
//    private ItemStock itemStock;

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

    @Column(name="termination_date")
    private LocalDateTime terminationDate;




    public void removeStock(int count) {
    }
}
