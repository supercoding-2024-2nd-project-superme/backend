package com.backend.superme.entity.view;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name = "parent_category_id")
    private Long parentCategoryId;

    @OneToMany
    @JoinColumn(name = "parent_categoryID")
    private Set<Category> subCategories;

}
