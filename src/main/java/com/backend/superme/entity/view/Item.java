package com.backend.superme.entity.view;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

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

    @Column(name ="item_name", nullable=false)
    private String itemName;

    @Column(name="main_img")
    private String mainImg;

    @Column(name = "descrption")
    private String description;

    @Column(name="color_option")
    private String colorOption;

    @Column(name="size_option")
    private String sizeOption;

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status")
    private StockStatus stockStatus;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    /*Todo :유저 연결후에 추가
    @ManyToOne
    @JoinColumn(name = "seller",referencedColumnName = "id")
    private User seller;
    */

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name="termination_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date terminationDate;

    public enum StockStatus {
        IN_STOCK, OUT_OF_STOCK, BACK_ORDER
    }

}
