package com.backend.superme.service.view;

import com.backend.superme.dto.view.CartItemDto;
import com.backend.superme.entity.view.Cart;
import com.backend.superme.entity.view.CartItem;
import com.backend.superme.entity.view.Item;
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
    /*Todo 유저 정보 연결 후
    private final UserRepository userRepository;
    */
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

   /*
   //Todo 유저 엔티티 연결 후
    public Long addCart(CartItemDto cartItemDto, String email) {
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        User user = userRepository.findByEmail(email);
        Cart cart =cartRepository.findByUserId(user.getId());

        if(cart ==null){
            cart =Cart.createCart(user);
            cartRepository.save(cart);
        }

        //현재 상품이 장바구니에 이미 있는지 조회
        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(),item.getId());

        //장바구니에 이미 상품이 있는경우, 기존 수량에서 수량에서 수량 추가
        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        }else {
            CartItem cartItem = CartItem.createCartItem(cart,item,cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
        */

}