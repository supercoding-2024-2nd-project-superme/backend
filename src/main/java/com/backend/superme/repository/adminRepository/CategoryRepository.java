package com.backend.superme.repository.adminRepository;

import com.backend.superme.entity.view.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
