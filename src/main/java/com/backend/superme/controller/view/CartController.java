package com.backend.superme.controller.view;

import com.backend.superme.dto.view.CartItemDto;
import com.backend.superme.entity.view.CartItem;
import com.backend.superme.service.user.UserService;
import com.backend.superme.service.view.CartItemService;
import com.backend.superme.service.view.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/cart")
@Tag(name = "장바구니 아이템(조회,수정,제거, 담기) API 입니다", description = "조회,수정,제거 담기 api 입니다.")
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Qualifier("userService")
    @Autowired
    private UserService userService;

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Autowired
    public CartController(CartService cartService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    // 아이템 담기 API
    @PostMapping("/add")
    @Operation(summary = "아이템을 담은 API 입니다.", description = "아이템을 조회할 수 있습니다.")
    public ResponseEntity<Long> addToCart(@Valid @RequestBody CartItemDto cartItemDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(-1L); // 유효성 검사 오류가 있는 경우 -1 반환
        }
        Long userId = Long.valueOf(principal.getName()); // 현재 사용자의 이메일 주소 가져오기
        try {
            Long cartItemId = cartService.addToCart(cartItemDto, userId); // 장바구니에 물품 추가
            return ResponseEntity.ok(cartItemId); // 성공적으로 추가된 경우 해당 카트 아이템의 ID 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(-1L); // 추가 실패 시 -1 반환
        }
    }

    //     장바구니 조회 API
    @GetMapping("/items")
    @Operation(summary = "장바구니를 조회하는 API 입니다.", description = "장바구니를 조회할 수 있습니다.")
    public ResponseEntity<List<CartItemDto>> getCartItems(Principal principal) {
        String email = principal.getName(); // 현재 사용자의 이메일 주소 가져오기
        List<CartItemDto> cartItems = cartItemService.getCartItems(email); // 현재 사용자의 장바구니 아이템 조회
        return ResponseEntity.ok(cartItems); // 조회된 장바구니 아이템 반환
//        return null;
    }

    // 상품 수정 API
    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<String> updateCartItem(@PathVariable Long cartItemId, @Valid @RequestBody CartItemDto cartItemDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(sb.toString()); // 유효성 검사 오류가 있는 경우 오류 메시지 반환
        }
        try {
            String email = principal.getName(); // 현재 사용자의 이메일 주소 가져오기
            cartItemService.updateCartItem(cartItemId, cartItemDto, email); // 장바구니 아이템 수정
            return ResponseEntity.ok("Cart item updated successfully"); // 수정 성공 메시지 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 수정 실패 시 에러 메시지 반환
        }
    }

    //     상품 제거 API
    @DeleteMapping("/items/{cartItemId}")
    @Operation(summary = "상품을 제거하는 API 입니다.", description = "상품을 제거할 수 있습니다.")
    public ResponseEntity<String> removeCartItem(@PathVariable Long cartItemId) {
        try {
            cartItemService.removeCartItem(cartItemId); // 장바구니 아이템 제거
            return ResponseEntity.ok("Cart item removed successfully"); // 제거 성공 메시지 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 제거 실패 시 에러 메시지 반환
        }
    }

    // 주문하기 API
    @PostMapping("/order")
    @Operation(summary = "상품을 주문하는 API 입니다.", description = "상품을 주문할 수 있습니다.")
    public ResponseEntity<String> order(Principal principal) {
        Long userId = Long.valueOf(principal.getName()); // 현재 사용자의 userId 가져오기
        try {
            cartService.order(userId); // 주문 처리를 userId를 사용하여 처리
            return ResponseEntity.ok("Order placed successfully"); // 주문 성공 메시지 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 주문 실패 시 에러 메시지 반환
        }
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
        List<CartItem> cartItems = cartService.getCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItems);
    }

}
