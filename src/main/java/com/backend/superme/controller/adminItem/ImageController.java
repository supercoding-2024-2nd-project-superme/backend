package com.backend.superme.controller.adminItem;

import com.backend.superme.dto.adminItemDto.ItemRequest;
import com.backend.superme.dto.adminItemDto.ItemRegistrationDTO;
import com.backend.superme.service.adminService.ImageService;
import com.backend.superme.service.adminService.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ImageController {


    private final ProductService productService;
    private ImageService imageService;



    public ResponseEntity<String> registerItem(@Validated @RequestBody ItemRegistrationDTO dto,
                                               @RequestParam("terminationDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime terminationDate) {
        // 종료 날짜를 DTO에 설정
        dto.setTermination_date(terminationDate);

        // 상품 등록 서비스 호출
        productService.registerItem(dto, terminationDate);

        return ResponseEntity.ok("Item registration successful");
    }


    @PostMapping("/image/upload")
    @ResponseBody
    public Map<String, Object> imageUpload(MultipartRequest request) {
        Map<String, Object> responseData = new HashMap<>();
        try {
            String s3Url = imageService.imageUpload(request);
            responseData.put("uploaded", true);
            responseData.put("url", s3Url);
            return responseData;
        } catch (IOException e) {
            responseData.put("uploaded", false);
            return responseData;
        }
    }
    // 이미지 수정 메서드
    @PostMapping("/image/update")
    public ResponseEntity<String> updateImage(@RequestParam("imageUrl") String imageUrl,
                                              @RequestParam("newImage") MultipartFile newImage) {
        try {
            // 기존 이미지 삭제
            imageService.deleteImage(imageUrl);

            // 새로운 이미지 업로드
            String newImageUrl = imageService.imageUpload((MultipartRequest) newImage);

            // 성공 메시지 반환
            return ResponseEntity.ok("Image updated successfully. New image URL: " + newImageUrl);
        } catch (IOException e) {
            // 이미지 업로드 도중 에러 발생 시 에러 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update image.");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateItem(@Validated @RequestBody ItemRequest dto) {
        productService.updateItem(dto);
        return ResponseEntity.ok("Item update successful");
    }


}