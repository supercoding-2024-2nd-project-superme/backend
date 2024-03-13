package com.backend.superme.service.view;

import com.backend.superme.repository.view.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    public CartService addCart;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository){
        this.cartItemRepository = cartItemRepository;
    }
    //Service methods
}
