package com.backend.superme.controller.adminItem;

import com.backend.superme.dto.adminItemDto.category.CategoryRequest;
import com.backend.superme.dto.adminItemDto.category.CategoryResponse;
import com.backend.superme.service.adminService.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")

public class CategoryAdminController {

    private final CategoryService categoryService;

    //관리자가 카테고리를 등록하는 api
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@RequestBody @Valid CategoryRequest request,
                                   @RequestHeader("Authorization") String user
    ) {
//        admin 이라는걸 알려줘야합니다..ㅠ
        System.out.println("user.getAuthorities() = admin ");
        return categoryService.create(request);
    }

}
