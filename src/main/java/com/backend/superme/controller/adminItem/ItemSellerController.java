package com.backend.superme.controller.adminItem;

import com.backend.superme.config.exception.BusinessException;
import com.backend.superme.config.exception.ErrorCode;
import com.backend.superme.dto.adminItemDto.CreateItemResponse;
import com.backend.superme.dto.adminItemDto.ItemRequest;
import com.backend.superme.dto.user.UserDto;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.service.adminService.implement.ImplItemService;
import com.backend.superme.service.adminService.implement.S3Service;
import com.backend.superme.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales")
//@Tag(name = "(판매자 권한) 상품 관련 api", description = "판매자의 상품 등록, 수정, 조회, 삭제 api입니다.")
public class ItemSellerController {

    private final ImplItemService itemService;
    private final S3Service s3Service;
    private final UserService userService;


    //(판매자) 상품 등록 + 이미지 추가 ( 필수 ) + 옵션 설정
    @ResponseStatus(HttpStatus.CREATED) //= 응답코드
    @PostMapping(value = "/items", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @Operation(summary = "상품 등록 api", description = "상품을 등록하는 api 입니다.")
    public CreateItemResponse addItem(@Valid @RequestPart ItemRequest itemRequest,
                                      @RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles,
                                      @RequestHeader("Authorization") String user) {


        String userEmail = userService.emailFromToken(user);

        return itemService.create(itemRequest, multipartFiles, userEmail);
    }


}

