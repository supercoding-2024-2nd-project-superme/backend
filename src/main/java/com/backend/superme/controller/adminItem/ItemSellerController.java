package com.backend.superme.controller.adminItem;

import com.backend.superme.config.exception.BusinessException;
import com.backend.superme.config.exception.ErrorCode;
import com.backend.superme.dto.adminItemDto.CreateItemResponse;
import com.backend.superme.dto.adminItemDto.ItemRequest;
import com.backend.superme.service.adminService.implement.ImplItemService;
import com.backend.superme.service.adminService.implement.S3Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales")
//@Tag(name = "(판매자 권한) 상품 관련 api", description = "판매자의 상품 등록, 수정, 조회, 삭제 api입니다.")
public class ItemSellerController {

    private final ImplItemService itemService;
    private final S3Service s3Service;


    //(판매자) 상품 등록 + 이미지 추가 ( 필수 ) + 옵션 설정
    @ResponseStatus(HttpStatus.CREATED) //= 응답코드
    @PostMapping(value = "/items", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @Operation(summary = "상품 등록 api", description = "상품을 등록하는 api 입니다.")
    public CreateItemResponse addItem(@Valid @RequestPart ItemRequest itemRequest,
                                      @RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles,
                                      @AuthenticationPrincipal User user) {

        return itemService.create(itemRequest, multipartFiles, user);
    }


//    @PostMapping("/images/upload") // 수정: 업로드 처리하는 URL을 /images/upload로 변경
//    public void uploadImages(@RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles) {
//        // 이미지를 업로드하고 처리하는 로직을 구현합니다.
//        // 이 부분에는 S3나 로컬 저장소에 이미지를 업로드하는 코드를 작성하시면 됩니다.
//
//        // 예시: 이미지를 업로드한 후 처리하는 로직
//        if (multipartFiles.isEmpty()) {
//            throw new BusinessException(ErrorCode.REQUIRED_IMAGE); // 이미지가 없는 경우
//        }
//
//        try {
//            // 예시: S3에 이미지를 업로드하는 코드
//            List<String> imageUrls = s3Service.upload(multipartFiles);
//            if (imageUrls.isEmpty()) {
//                throw new BusinessException(ErrorCode.UPLOAD_ERROR_IMAGE); // 이미지 업로드에 실패한 경우
//            }
//        } catch (Exception e) {
//            throw new BusinessException(ErrorCode.UPLOAD_ERROR_IMAGE); // 이미지 업로드에 실패한 경우
//        }
//    }
}

