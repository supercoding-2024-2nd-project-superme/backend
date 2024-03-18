package com.backend.superme.controller.adminItem;

import com.backend.superme.dto.adminItemDto.category.CategoryRequest;
import com.backend.superme.dto.adminItemDto.category.CategoryResponse;
import com.backend.superme.service.adminService.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "(관리자 권한) 카테고리 관련 api", description = "관리자만 접근 가능한 카테고리 관련 api입니다.")
public class CategoryAdminController {

    private final CategoryService categoryService;

    //관리자가 카테고리를 등록하는 api
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "카테고리 등록 api", description = "관리자가 카테고리를 등록하는 api 입니다.")
    public CategoryResponse create(@RequestBody @Valid CategoryRequest request,
                                   @RequestHeader("Authorization") String user
    ) {
//        admin 이라는걸 알려줘야합니다..ㅠ
        System.out.println("user.getAuthorities() = admin ");
        return categoryService.create(request);
    }

}
