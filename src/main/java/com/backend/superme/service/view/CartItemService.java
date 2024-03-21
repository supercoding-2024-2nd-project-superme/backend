package com.backend.superme.service.view;

import com.backend.superme.config.global.BusinessException;
import com.backend.superme.dto.view.CartItemDto;
import com.backend.superme.dto.view.CartItemUpdateDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.CartItem;
import com.backend.superme.entity.view.Item;
import com.backend.superme.entity.view.ItemStock;
import com.backend.superme.repository.view.CartItemRepository;
import com.backend.superme.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ItemService itemService;



    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, ModelMapper modelMapper, UserService userService, ItemService itemService) {
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.itemService = itemService;
    }


    //상품 정보 수정 메서드
    public void updateCartItem(Long cartItemId, @Valid CartItemUpdateDto cartItemaddDto, Long userId) {      //특정 사용자의 특정 카트 아이템을 수정하는 메서드
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if (cartItem != null) {
            cartItem.setId(cartItemaddDto.getItemId());
            cartItemRepository.save(cartItem);
        }
    }

    //상품 정보 제거 메서드
    public void removeCartItem(Long cartItemId) {
        // 특정 카트 아이템을 삭제하는 메서드
        cartItemRepository.deleteById(cartItemId);
    }




    //사용자 장바구니 조회 메서드
    public List<CartItemDto> getCartItems(String userId) {
        // 특정 사용자의 장바구니에 담긴 상품 목록을 조회하는 메서드
        List<CartItem> cartItems = cartItemRepository.findByUserId(Long.valueOf(userId));
        return cartItems.stream()
                .map(cartItem -> modelMapper.map(cartItem, CartItemDto.class))
                .collect(Collectors.toList());
    }

    // Entity -> DTO 변환하는 메서드
    private CartItemDto convertToDto(CartItem cartItem) {
        // CartItemEntity -> CartItemDto 변환하는 로직
        return modelMapper.map(cartItem, CartItemDto.class);
    }


}
