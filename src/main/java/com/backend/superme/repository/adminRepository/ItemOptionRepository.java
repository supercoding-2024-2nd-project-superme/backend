package com.backend.superme.repository.adminRepository;

import com.backend.superme.entity.view.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemStock, String> {
}
