package com.backend.superme.controller.adminItem;

import com.backend.superme.dto.adminItemDto.category.CategoryRequest;
import com.backend.superme.dto.adminItemDto.category.CategoryResponse;
import com.backend.superme.service.adminService.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "(관리자 권한) 카테고리 등록하는 API 입니다.", description = "관리자만 접근 가능한 카테고리 관련 API 입니다.")
@Slf4j
public class CategoryAdminController {

    private final CategoryService categoryService;

    //관리자가 카테고리를 등록하는 api
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "카테고리 등록 api", description = "관리자가 카테고리를 등록하는 api 입니다.")
    public CategoryResponse create(@RequestBody @Valid CategoryRequest request,
                                   @RequestHeader("Authorization") String user) {

        log.info("catregory 등록 요청이 들어왔습니다 {}", request);
        if(request.categoryName().isEmpty()){
            log.error("category 이름입력하세요!!!!!!!");
        }

        return categoryService.create(request);
    }

}
