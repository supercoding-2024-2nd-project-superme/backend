package com.backend.superme.service.view;

import com.backend.superme.dto.view.CartItemDto;
import com.backend.superme.entity.view.CartItem;
import com.backend.superme.repository.view.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, ModelMapper modelMapper){
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
    }

   //상품 정보 수정 메서드
   public void updateCartItem(Long cartItemId, CartItemDto cartItemDto, String email) {      //특정 사용자의 특정 카트 아이템을 수정하는 메서드
       CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
       if (cartItem != null) {
           cartItem.setId(cartItemDto.getItemId());
           cartItemRepository.save(cartItem);
       }
   }

    //상품 정보 제거 메서드
       public void removeCartItem(Long cartItemId) {
           // 특정 카트 아이템을 삭제하는 메서드
           cartItemRepository.deleteById(cartItemId);
       }


       //사용자 장바구니 조회 메서드
       public List<CartItemDto> getCartItems(String email) {
           // 특정 사용자의 장바구니에 담긴 상품 목록을 조회하는 메서드
           List<CartItem> cartItems = cartItemRepository.findByUser(email);
           return cartItems.stream()
                   .map(cartItem -> modelMapper.map(cartItem, CartItemDto.class))
                   .collect(Collectors.toList());
       }

       // Entity를 DTO로 변환하는 메서드
       private CartItemDto convertToDto(CartItem cartItem) {
           // CartItemEntity를 CartItemDto로 변환하는 로직
           return new CartItemDto(cartItem.getId());
       }
}
