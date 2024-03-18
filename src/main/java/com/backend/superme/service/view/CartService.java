package com.backend.superme.service.view;

import com.backend.superme.dto.view.CartItemDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.Cart;
import com.backend.superme.entity.view.CartItem;
import com.backend.superme.entity.view.Item;
import com.backend.superme.repository.user.UserRepository;
import com.backend.superme.repository.view.CartItemRepository;
import com.backend.superme.repository.view.CartRepository;
import com.backend.superme.repository.view.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    // 물품을 장바구니에 추가하는 메서드
    public Long addToCart(CartItemDto cartItemDto, String email) {
        UserEntity user = getUserByEmail(email); // 이메일로부터 사용자 정보 가져오기
        Item item = getItemById(cartItemDto.getItemId()); // 상품 아이디로부터 상품 정보 가져오기

        Cart cart = getOrCreateCart(user); // 사용자의 장바구니 가져오거나 생성하기

        // 이미 있는 상품인지 확인하고 수량 추가 또는 새로운 상품 추가하기
        CartItem savedCartItem = getSavedCartItem(cart, item);
        if (savedCartItem != null) {
            savedCartItem.setOrdered_qty(savedCartItem.getOrdered_qty() + cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            // 장바구니에 새로운 상품을 추가함
            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setCart(cart);
            cartItem.setOrdered_qty(cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    // 장바구니에 있는 상품의 수량을 수정하는 메서드
    public void updateCartItemQty(Long cartItemId, int newQty) {
        CartItem cartItem = getCartItemById(cartItemId); // 장바구니 아이템 가져오기
        cartItem.setOrdered_qty(newQty); // 수량 수정
    }

    // 장바구니에서 상품을 제거하는 메서드
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId); // 장바구니 아이템 삭제
    }

    // 이메일로부터 사용자 정보 가져오는 메서드
    private UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    // 상품 아이디로부터 상품 정보 가져오는 메서드
    private Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
    }

    // 사용자의 장바구니 가져오거나 생성하는 메서드
    private Cart getOrCreateCart(UserEntity user) {
        Cart cart = (Cart) cartRepository.findByUserId(user.getId());
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cart;
    }

    // 장바구니에 이미 있는 상품인지 확인하고 가져오는 메서드
    private CartItem getSavedCartItem(Cart cart, Item item) {
        return cartItemRepository.findByCartAndItemId(cart, item.getId());
    }


    // 장바구니 아이템 아이디로부터 장바구니 아이템 가져오는 메서드
    private CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found with ID: " + cartItemId));
    }

    public void order(String email) {
    }
}