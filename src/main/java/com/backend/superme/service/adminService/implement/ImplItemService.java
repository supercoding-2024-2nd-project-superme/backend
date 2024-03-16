package com.backend.superme.service.adminService.implement;

import com.backend.superme.config.exception.BusinessException;
import com.backend.superme.dto.adminItemDto.*;
import com.backend.superme.entity.ItemImgEntity.AdminItemImageEntity;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.Category;
import com.backend.superme.entity.view.Item;
import com.backend.superme.repository.adminRepository.AdminItemCategoryRepository;
import com.backend.superme.repository.adminRepository.AdminItemImageRepository;
import com.backend.superme.repository.adminRepository.AdminItemRepository;
import com.backend.superme.repository.user.UserRepository;
import com.backend.superme.service.adminService.adminItemService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.backend.superme.config.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImplItemService implements adminItemService {

    private final S3Service s3Service;
    private final AdminItemRepository itemRepository;
    private final AdminItemImageRepository itemImageRepository;
    private final UserRepository memberRepository;
    private final AdminItemCategoryRepository categoryRepository;


    @Override
    @Transactional
    public CreateItemResponse create(ItemRequest itemRequest, List<MultipartFile> multipartFiles, User user) {
        //유저 찾기
        UserEntity member = getMember(user);
//        System.out.println("사용자 정보 {} " + member);
//
//        //같은 이름의 상품이 있으면 예외처리, 같은 이름의 상품을 등록할 수 없음
//        if (itemRepository.findByItemName(itemRequest.itemName()).isPresent()) {
//            throw new BusinessException(DUPLICATE_ITEM, "이미 존재하는 상품입니다");
//        }
//
//        //카테고리 존재 확인
//        Category category = categoryRepository.findById(itemRequest.categoryId()).orElseThrow(
//                () -> new BusinessException(NOT_FOUND_CATEGORY));

        Item item = Item.builder()
                .itemName(itemRequest.itemName())
                .price(itemRequest.price())
                .description(itemRequest.description())
//                .category(category)
                .seller(member)
                .sizeOption(itemRequest.sizeOption())
                .colorOption(itemRequest.colorOption())
                .build();

        Item savedItem = itemRepository.save(item);

        // S3 저장
        System.out.println("S3 이미지 업로드");
        List<String> imageUrls = s3Service.upload(multipartFiles);

        //이미지 1장 이상 등록안했을 때 에러처리
        if (imageUrls.isEmpty()) {
            throw new BusinessException(REQUIRED_IMAGE, "이미지는 필수로 등록해야합니다");
        }
        System.out.println("업로드된 이미지 URL : {}" + imageUrls);

        // 이미지 DB 저장
        List<AdminItemImageEntity> imageList = imageUrls.stream()
                .map(url -> AdminItemImageEntity.builder().imageUrl(url).item(savedItem).build())
                .toList();

        itemImageRepository.saveAll(imageList);

        List<Long> itemImgIds = imageList.stream().map(AdminItemImageEntity::getItemImageId).toList();

        return getCreateItemResponse(savedItem, imageUrls, itemImgIds);

    }

    //  ItemResponse 코드 중복 방지
    private CreateItemResponse getCreateItemResponse(Item item, List<String> imageUrls, List<Long> itemImgIds) {
        return new CreateItemResponse(
                item.getId(),
                item.getItemName(),
//                item.getCategory().getCategoryId(),
                item.getColorOption(),
                item.getSizeOption(),
                item.getDescription(),
                itemImgIds,
                imageUrls

        );


    }


    @Override
    public UpdateItemResponse update(Long itemId, UpdateItemRequest itemRequest, List<MultipartFile> multipartFiles, User user) {
        return null;
    }

    @Override
    public void delete(Long itemId, User user) {

    }

    @Override
    public SellerItemsResponse getSellerAll(Pageable pageable, User user) {
        return null;
    }

    @Override
    public ItemResponse getOne(Long itemId) {
        return null;
    }

    @Override
    public ItemPageResponse getAll(Pageable pageable, Long categoryId) {
        return null;
    }


    private UserEntity getMember(User user) {
        return memberRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new BusinessException(NOT_FOUND_MEMBER));
    }
}
