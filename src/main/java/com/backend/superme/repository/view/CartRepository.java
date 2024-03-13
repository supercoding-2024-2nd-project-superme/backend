package com.backend.superme.repository.view;

import com.backend.superme.entity.view.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUserId(Long userId);
}
