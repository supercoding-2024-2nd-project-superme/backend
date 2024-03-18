package com.backend.superme.entity.ItemImgEntity;

import com.backend.superme.constant.base.BaseEntity;
import com.backend.superme.entity.view.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adminItemImage")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AdminItemImageEntity extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "admin_item_iamge_id")
    private Long itemImageId;

    @Column(name = "admin_item_url", nullable = false)
    private String imageUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item", referencedColumnName = "id", nullable = false)
    private Item item;

    @Builder
    public AdminItemImageEntity(String imageUrl, Item item) {
        this.imageUrl = imageUrl;
        this.item = item;
    }

}
