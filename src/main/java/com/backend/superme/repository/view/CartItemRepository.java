package com.backend.superme.repository.view;

import com.backend.superme.entity.view.CartItem;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

   @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.item.id = :itemId")

   CartItem findCartIdAndItemId(Long cartId, Long itemId);

   CartItem findByCartIdAndItemId(Long cartId, Long itemId);
}
