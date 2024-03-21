package com.backend.superme.service.view;

import com.backend.superme.dto.view.CartItemAddDto;
import com.backend.superme.dto.view.CartItemDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.*;
import com.backend.superme.repository.user.UserRepository;
import com.backend.superme.repository.view.CartItemRepository;
import com.backend.superme.repository.view.CartRepository;
import com.backend.superme.repository.view.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    public List<CartItem> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    // 물품을 장바구니에 추가하는 메서드
    public Long addToCart(@Valid CartItemAddDto cartItemDto, Long userId) {
        UserEntity user = getUserById(userId); // userId로부터 사용자 정보 가져오기
        Item item = getItemById(cartItemDto.getItemId()); // 상품 아이디로부터 상품 정보 가져오기
        Cart cart = getOrCreateCart(user); // 사용자의 장바구니 가져오거나 생성하기

        // 이미 있는 상품인지 확인하고 수량 추가 또는 새로운 상품 추가하기
        CartItem savedCartItem = getSavedCartItem(cart, item);
        if (savedCartItem != null) {
            savedCartItem.setOrderedQty(savedCartItem.getOrderedQty() + cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            // 장바구니에 새로운 상품을 추가함
            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setCart(cart);
            cartItem.setOrderedQty(cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    // 장바구니에 있는 상품의 수량을 수정하는 메서드
    public void updateCartItemQty(Long cartItemId, int newQty) {
        CartItem cartItem = getCartItemById(cartItemId); // 장바구니 아이템 가져오기
        cartItem.setOrderedQty(newQty); // 수량 수정
    }

    // 장바구니에서 상품을 제거하는 메서드
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId); // 장바구니 아이템 삭제
    }

    // 사용자 정보 가져오는 메서드
    private UserEntity getUserById(Long userId) {
        return userRepository.findById(Long.parseLong(String.valueOf(userId)))
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
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

    public void order(Long userId) { // 수정된 부분
        UserEntity user = getUserById(userId); // userId로부터 사용자 정보 가져오기
        Cart cart = getOrCreateCart(user); // 사용자의 장바구니 가져오거나 생성하기

        if (cartItemRepository.countByCart(cart) == 0) {
            throw new RuntimeException("장바구니가 비어있습니다.");
        }
        // CartItem을 OrderItem으로 변환
        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toList());

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);

        orderService.saveOrder(order);

        cart.getCartItems().clear();
        cartRepository.save(cart);
    }


    private OrderItem convertToOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        // orderItem 필드 설정
        orderItem.setOrderedQty(cartItem.getOrderedQty());
        orderItem.setItem(cartItem.getItem());
        orderItem.setOrderPrice(cartItem.getItem().getPrice());
        orderItem.setRegTime(new Date());
        orderItem.setUpdateTime(new Date());
        return orderItem;
    }
    public void addItemToCart(CartItemDto cartItemDto, String firstImgUrl) {
        // 첫 번째 이미지 URL을 사용하여 처리
        // 여기에서는 단순히 예시로 작성하였습니다
        System.out.println("First Image URL: " + firstImgUrl);
        // 나머지 로직 추가
    }


    public List<CartItem> getCartItems() {
        // 장바구니에 있는 모든 장바구니 아이템을 가져와서 반환
        return cartItemRepository.findAll();
    }
}