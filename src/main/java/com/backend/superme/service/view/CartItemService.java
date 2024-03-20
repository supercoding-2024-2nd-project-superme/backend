package com.backend.superme.service.view;

import com.backend.superme.config.global.BusinessException;
import com.backend.superme.dto.view.CartItemDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.CartItem;
import com.backend.superme.entity.view.Item;
import com.backend.superme.entity.view.ItemStock;
import com.backend.superme.repository.view.CartItemRepository;
import com.backend.superme.service.user.UserService;
import jakarta.transaction.Transactional;
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

    //장바구니 -> 주문
    // 장바구니에 상품 추가하는 메서드
    @Transactional
    public void addToCart(CartItemDto cartItemDto, Long userId) {
        Optional<UserEntity> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            List<Item> items = itemService.findByUserId(userId);

            // Added code to fetch the first item from the list
            if (items.isEmpty()) {
                throw new BusinessException.ItemNotFoundException("사용자의 상품 목록이 비어있습니다.");
            }
            Item item = items.get(0);

            BigDecimal totalPrice = item.getPrice().multiply(BigDecimal.valueOf(cartItemDto.getCount()));

            int totalStock = item.getItemStocks().stream().mapToInt(ItemStock::getStockQty).sum();
            if (totalStock < cartItemDto.getCount()) {
                throw new BusinessException.InsufficientStockException("재고가 부족합니다.");
            }

            BigDecimal userBalance = user.getBalance();
            if (userBalance.compareTo(totalPrice) < 0) {
                throw new BusinessException.InsufficientBalanceException("잔액이 부족합니다.");
            }

            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setOrderedQty(cartItemDto.getCount());
            cartItemRepository.save(cartItem);

            int remainingQuantity = cartItemDto.getCount();
            for (ItemStock stock : item.getItemStocks()) {
                int availableStock = stock.getStockQty();
                if (availableStock >= remainingQuantity) {
                    stock.setStockQty(availableStock - remainingQuantity);
                    break;
                } else {
                    throw new BusinessException.UserNotFoundException("사용자를 찾을 수 없습니다.");
                }
            }
            userService.deductBalance(userId, totalPrice);
        }
    }
}