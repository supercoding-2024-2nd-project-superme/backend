package com.backend.superme.entity.view;

import jakarta.persistence.*;
import lombok.*;
import com.backend.superme.constant.item.StockStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Column(name = "description")
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
    private LocalDateTime registrationDate;

    @Column(name="termination_date")
    private LocalDateTime terminationDate;

    public void removeStock(int count) {
        //Todo 수량 변경 로직 필요
    }
}
