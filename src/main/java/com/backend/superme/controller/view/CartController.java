//package com.backend.superme.controller.view;
//
//import com.backend.superme.dto.view.CartItemDto;
//import com.backend.superme.service.view.CartService;
//import com.backend.superme.service.view.CartItemService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.List;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/cart")
//public class CartController {
//    private final CartService cartService;
//    private final CartItemService cartItemService;
//
//
//    // 아이템 담기 API
//    @PostMapping("/add")
//    public ResponseEntity<Long> addToCart(@Valid @RequestBody CartItemDto cartItemDto, BindingResult bindingResult, Principal principal) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder sb = new StringBuilder();
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                sb.append(fieldError.getDefaultMessage()).append("; ");
//            }
//            return ResponseEntity.badRequest().body(-1L); // 유효성 검사 오류가 있는 경우 -1 반환
//        }
//        String email = principal.getName(); // 현재 사용자의 이메일 주소 가져오기
//        try {
//            Long cartItemId = cartService.addToCart(cartItemDto, email); // 장바구니에 물품 추가
//            return ResponseEntity.ok(cartItemId); // 성공적으로 추가된 경우 해당 카트 아이템의 ID 반환
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(-1L); // 추가 실패 시 -1 반환
//        }
//    }
//
////     장바구니 조회 API
//    @GetMapping("/items")
//    public ResponseEntity<List<CartItemDto>> getCartItems(Principal principal) {
//        String email = principal.getName(); // 현재 사용자의 이메일 주소 가져오기
////        List<CartItemDto> cartItems = cartItemService.getCartItems(email); // 현재 사용자의 장바구니 아이템 조회
////        return ResponseEntity.ok(cartItems); // 조회된 장바구니 아이템 반환
//        return null;
//    }
//
//    // 상품 수정 API
//    @PutMapping("/items/{cartItemId}")
//    public ResponseEntity<String> updateCartItem(@PathVariable Long cartItemId, @Valid @RequestBody CartItemDto cartItemDto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder sb = new StringBuilder();
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                sb.append(fieldError.getDefaultMessage()).append("; ");
//            }
//            return ResponseEntity.badRequest().body(sb.toString()); // 유효성 검사 오류가 있는 경우 오류 메시지 반환
//        }
//        try {
////            cartItemService.updateCartItem(cartItemId, cartItemDto); // 장바구니 아이템 수정
//            return ResponseEntity.ok("Cart item updated successfully"); // 수정 성공 메시지 반환
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage()); // 수정 실패 시 에러 메시지 반환
//        }
//    }
//
////     상품 제거 API
//    @DeleteMapping("/items/{cartItemId}")
//    public ResponseEntity<String> removeCartItem(@PathVariable Long cartItemId) {
//        try {
////            cartItemService.removeCartItem(cartItemId); // 장바구니 아이템 제거
//            return ResponseEntity.ok("Cart item removed successfully"); // 제거 성공 메시지 반환
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage()); // 제거 실패 시 에러 메시지 반환
//        }
//    }
//
//    // 주문하기 API \
//    @PostMapping("/order")
//    public ResponseEntity<String> order(Principal principal) {
//        String email = principal.getName(); // 현재 사용자의 이메일 주소 가져오기
//        try {
////            cartService.order(email); // 주문 처리
//            return ResponseEntity.ok("Order placed successfully"); // 주문 성공 메시지 반환
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage()); // 주문 실패 시 에러 메시지 반환
//        }
//    }
//
//
//}
//
