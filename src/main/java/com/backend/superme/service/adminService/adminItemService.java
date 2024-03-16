package com.backend.superme.service.adminService;

import com.backend.superme.config.user.UserPrincipal;
import com.backend.superme.dto.adminItemDto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Service
public interface adminItemService {
    // 상품을 생성하고 생성된 상품의 정보를 반환합니다.
    CreateItemResponse create(ItemRequest itemRequest, List<MultipartFile> multipartFiles, User user);

    // 주어진 상품 ID 에 해당하는 상품을 업데이트하고 업데이트 된 상품의 정보를 반환합니다.
    UpdateItemResponse update(Long itemId, UpdateItemRequest itemRequest, List<MultipartFile> multipartFiles, User user);

    // 주어진 상품 ID에 해당하는
    void delete(Long itemId, User user);

    SellerItemsResponse getSellerAll(Pageable pageable, User user);

    ItemResponse getOne(Long itemId);

    ItemPageResponse getAll(Pageable pageable, Long categoryId);
}
