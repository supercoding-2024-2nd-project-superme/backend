package com.backend.superme.service.adminService.implement;


import com.backend.superme.dto.adminItemDto.category.CategoryRequest;
import com.backend.superme.dto.adminItemDto.category.CategoryResponse;
import com.backend.superme.entity.view.Category;
import com.backend.superme.repository.adminRepository.CategoryRepository;
import com.backend.superme.service.adminService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    //값 출력
    @Override
    public List<CategoryResponse> getAll(CategoryRequest request) {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map((category) -> {
                    return new CategoryResponse(category.getId(), category.getName());
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional // 생성해야해서 읽기 전용해제
    public CategoryResponse create(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.categoryName())
                .build();

        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponse(savedCategory.getId(), savedCategory.getName());
    }

    @Override
    public void delete(Long categoryId) {

    }

    @Override
    public void update(CategoryRequest request, Long categoryId) {

    }

}
