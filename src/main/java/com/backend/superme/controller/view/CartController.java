package com.backend.superme.controller.view;

import com.backend.superme.dto.view.CartItemAddDto;
import com.backend.superme.dto.view.CartItemDto;
import com.backend.superme.dto.view.CartItemUpdateDto;
import com.backend.superme.entity.ItemImgEntity.AdminItemImageEntity;
import com.backend.superme.entity.view.CartItem;
import com.backend.superme.repository.adminRepository.AdminItemImageRepository;
import com.backend.superme.repository.view.CartItemRepository;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/carts")
@Tag(name = "장바구니 아이템(조회,수정,제거, 담기) API", description = "조회,수정,제거 담기 api 입니다.")
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final AdminItemImageRepository adminItemImageRepository; // AdminItemImageRepository 추가


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CartItemRepository cartItemRepository;

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Autowired
    public CartController(CartService cartService, CartItemService cartItemService, AdminItemImageRepository adminItemImageRepository, AdminItemImageRepository adminItemImageRepository1) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.adminItemImageRepository = adminItemImageRepository1;
    }

        //첫번째 링크 넣기
        public void addItemToCart(CartItemDto cartItemDto, @RequestParam("profileImage") List<String> imageUrls) {
            try {
                if (imageUrls.isEmpty()) {
                    throw new IllegalArgumentException("이미지 링크가 비어 있습니다.");
                }
                // 첫 번째 이미지 URL을 사용하여 AdminItemImageEntity 가져오기
                String firstImgUrl = imageUrls.get(0);
                AdminItemImageEntity adminItemImageEntity = adminItemImageRepository.findByImageUrl(firstImgUrl);

                //아이템 이름을 가져오기 위해 아이템 엔터티에서 정보 가져오기
                String itemName = cartItemDto.getItemName(); // 아이템 이름 가져오기

                // CartItemDto에서 필요한 정보 추출하여 CartItemEntity 생성
                CartItem cartItem = new CartItem();
                cartItem.setItemName(itemName); // 아이템 이름 설정
                cartItem.setOrderedQty(cartItemDto.getCount()); // 수량 설정
                cartItem.setPrice(cartItemDto.getPrice()); // 가격 설정
                cartItem.setAdminItemImage(adminItemImageEntity); // 이미지 엔터티 추가

                // 나머지 로직 (예: 사용자 ID 설정 등)

                // CartItemEntity 저장
                cartItemRepository.save(cartItem);
            } catch (Exception e) {
                e.printStackTrace(); // 오류 발생 시 콘솔에 출력
                // 오류 처리 로직 추가 (예: 예외 처리, 로깅 등)
            }
        }

        // 아이템 담기 API
        @PostMapping("/add")
        @Operation(summary = "아이템을 담은 API 입니다.", description = "아이템을 조회할 수 있습니다.")
        public ResponseEntity<Long> addToCart(@Valid @RequestBody CartItemAddDto cartItemAddDto, BindingResult bindingResult, Principal principal) {
            if (bindingResult.hasErrors()) {
                StringBuilder sb = new StringBuilder();
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                for (FieldError fieldError : fieldErrors) {
                    sb.append(fieldError.getDefaultMessage()).append("; ");
                }
                return ResponseEntity.badRequest().body(-1L); // 유효성 검사 오류가 있는 경우 -1 반환
            }
            String email = String.valueOf(Long.valueOf(principal.getName())); // 현재 사용자의 이메일 주소 가져오기
            try {
                Long cartItemId = cartService.addToCart(cartItemAddDto, email); // 장바구니에 물품 추가
                return ResponseEntity.ok(cartItemId); // 성공적으로 추가된 경우 해당 카트 아이템의 ID 반환
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(-1L); // 추가 실패 시 -1 반환
            }
        }

        //장바구니 조회 API
        @GetMapping("/items")
        @Operation(summary = "장바구니를 조회하는 API(게스트)", description = "장바구니를 조회할 수 있습니다.")
        public ResponseEntity<List<CartItemDto>> getCartItems(Principal principal) {
            // 장바구니 조회 로직
            List<CartItem> cartItems = cartService.getCartItems();

            // CartItem 객체를 CartItemDto 객체로 변환
            List<CartItemDto> cartItemDtos = cartItems.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(cartItemDtos); // 조회된 장바구니 아이템 반환
        }
    private CartItemDto convertToDto(CartItem cartItem) {
        // CartItem 객체를 CartItemDto 객체로 변환하는 로직 구현
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setItemId(cartItem.getItem().getId());
        cartItemDto.setCount(cartItem.getOrderedQty());
        // 필요한 다른 필드도 여기에 매핑
        return cartItemDto;
    }

        //사용자 카트 조회
        @GetMapping("/cart/{userId}")
        @Operation(summary = "해당 유저의 보관하고 있는 장바구니",description = "")
        public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String email) {
            List<CartItem> cartItems = cartService.getCartItemsByUserEmail(email);
            return ResponseEntity.ok(cartItems);
        }

        // 상품 수정 API
        @PutMapping("/cart/items/{cartItemId}")
        @Operation(summary ="장바구니를 수정하는 API 입니다.", description = "장바구니에 있는 물품 수량 변경할수 있습니다.")
        public ResponseEntity<String> updateCartItem(@PathVariable Long cartItemId, @Valid @RequestBody CartItemUpdateDto cartItemUpdateDto, BindingResult bindingResult, Principal principal) {
            if (bindingResult.hasErrors()) {
                StringBuilder sb = new StringBuilder();
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                for (FieldError fieldError : fieldErrors) {
                    sb.append(fieldError.getDefaultMessage()).append("; ");
                }
                return ResponseEntity.badRequest().body(sb.toString()); // 유효성 검사 오류가 있는 경우 오류 메시지 반환
            }
            try {
                String email = principal.getName(); // 현재 사용자의 ID 가져오기
                cartItemService.updateCartItem(cartItemId, cartItemUpdateDto, email); // 장바구니 아이템 수정
                return ResponseEntity.ok("Cart item updated successfully"); // 수정 성공 메시지 반환
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage()); // 수정 실패 시 에러 메시지 반환
            }
        }

        //상품 제거 API
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
            String email = principal.getName(); // 현재 사용자의 이메일 주소 가져오기
            try {
                cartService.order(email);  // 주문 처리를 userId를 사용하여 처리
                return ResponseEntity.ok("Order placed successfully"); // 주문 성공 메시지 반환
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage()); // 주문 실패 시 에러 메시지 반환
            }
        }


}
