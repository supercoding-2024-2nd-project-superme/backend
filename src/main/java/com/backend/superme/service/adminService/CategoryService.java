package com.backend.superme.service.adminService;

import com.backend.superme.dto.adminItemDto.category.CategoryRequest;
import com.backend.superme.dto.adminItemDto.category.CategoryResponse;
import com.backend.superme.entity.view.Category;

import java.util.List;

public interface CategoryService {
    // 전체 조회
    List<CategoryResponse> getAll(CategoryRequest request);

    //생성
    CategoryResponse create(CategoryRequest request);

    //삭제
    void delete(Long categoryId);

    //수정
    void update(CategoryRequest request, Long categoryId);
}
