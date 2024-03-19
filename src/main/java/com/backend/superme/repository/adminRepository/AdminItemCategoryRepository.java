package com.backend.superme.repository.adminRepository;

import com.backend.superme.entity.view.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminItemCategoryRepository extends JpaRepository<Category, Long> {
}

