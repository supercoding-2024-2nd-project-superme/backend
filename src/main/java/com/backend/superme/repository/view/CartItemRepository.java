package com.backend.superme.repository.view;

import com.backend.superme.entity.view.Cart;
import com.backend.superme.entity.view.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
   CartItem findCartItemByCartIdAndItem_Id(Long cartId, Long itemId);
   CartItem findByCartAndItemId(Cart cart, Long itemId);
}