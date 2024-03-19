package com.backend.superme.repository.view;

import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.Cart;
import com.backend.superme.entity.view.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//   CartItem findCartItemByCartIdAndItem_Id(Long cartId, Long itemId);
   CartItem findByCartAndItemId(Cart cart, Long itemId);

    List<CartItem> findByUserId(Long userId);
   List<CartItem> findByCart(Cart cart);

    int countByCart(Cart cart);

}