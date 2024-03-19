package com.backend.superme.entity.view;

import com.backend.superme.constant.base.BaseEntity;
import com.backend.superme.dto.adminItemDto.category.CategoryRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_category_id")
    private Long parentCategoryId;

    @OneToMany
    @JoinColumn(name = "parent_categoryID")
    private Set<Category> subCategories;


    @Builder
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void updateCategory(CategoryRequest request) {
        this.name = request.categoryName();
    }
}
