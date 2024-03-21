package com.backend.superme.service.adminService.implement;

import com.backend.superme.config.global.BusinessException;
import com.backend.superme.dto.adminItemDto.*;
import com.backend.superme.entity.ItemImgEntity.AdminItemImageEntity;
import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.Category;
import com.backend.superme.entity.view.Item;
import com.backend.superme.entity.view.ItemStock;
import com.backend.superme.repository.adminRepository.AdminItemCategoryRepository;
import com.backend.superme.repository.adminRepository.AdminItemImageRepository;
import com.backend.superme.repository.adminRepository.AdminItemRepository;
import com.backend.superme.repository.adminRepository.ItemOptionRepository;
import com.backend.superme.repository.user.UserRepository;
import com.backend.superme.service.adminService.adminItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.backend.superme.config.global.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImplItemService implements adminItemService {

    private final S3Service s3Service;
    private final AdminItemRepository itemRepository;
    private final AdminItemImageRepository itemImageRepository;
    private final UserRepository memberRepository;
    private final AdminItemCategoryRepository categoryRepository;
    private final ItemOptionRepository itemOptionRepository;


    @Override
    @Transactional
    public CreateItemResponse create(ItemRequest itemRequest, List<MultipartFile> multipartFiles) {
        //유저 찾기
//        Optional<UserEntity> memberOptional = memberRepository.findByEmail(user);
//        UserEntity member = memberOptional.orElseThrow(() -> new NoSuchElementException("User not found with email: " + user));

//        System.out.println("사용자 정보 {} " + member);

        //---------------

        //카테고리 존재 확인
        Category category = categoryRepository.findByName(itemRequest.categoryName()).orElseThrow(
                () -> new BusinessException(NOT_FOUND_CATEGORY));

        // Make an item and save
        Item item = new Item();
        item.setName(itemRequest.itemName());
        item.setPrice(itemRequest.price());
        item.setDescription(itemRequest.description());
        item.setCategory(category);
//        item.setSeller(member);

        Item savedItem = itemRepository.save(item);

        // ItemStocks 설정
        List<ItemStock> itemStocks = new ArrayList<>();
        List<ItemRequest.ItemStockRequest> itemStockRequests = itemRequest.itemStocks();
        if (itemStockRequests == null || itemStockRequests.isEmpty()) {
            throw new BusinessException(FORBIDDEN_ERROR, "Item stocks cannot be null or empty");
        }
        for (ItemRequest.ItemStockRequest stockRequest : itemStockRequests) {
            ItemStock stock = new ItemStock();
            stock.setSize(stockRequest.size());
            stock.setColor(stockRequest.color());
            stock.setStockQty(stockRequest.stockQty());
            stock.setItem(savedItem); // 참조를 savedItem으로 변경합니다.

            itemStocks.add(stock);
        }

// itemStocks를 DB에 저장
        itemOptionRepository.saveAll(itemStocks);
        savedItem.setItemStocks(itemStocks); // 설정된 Item 객체의 ItemStocks 설정
        Item finalSavedItem = itemRepository.save(savedItem); // 다시 Item 저장

        //-----------------

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
                .map(url -> AdminItemImageEntity.builder()
                        .imageUrl(url)
                        .item(savedItem) // savedItem 객체를 바로 넘김
                        .build())
                .toList();
        itemImageRepository.saveAll(imageList);
        List<Long> itemImgIds = imageList.stream().map(AdminItemImageEntity::getItemImageId).toList();

        return getCreateItemResponse(savedItem, imageUrls, itemImgIds);

    }

    //  ItemResponse 코드 중복 방지

    private CreateItemResponse getCreateItemResponse(Item item, List<String> imageUrls, List<Long> itemImgIds) {

        List<String> colorOptions = item.getItemStocks().stream()
                .map(ItemStock::getColor)
                .distinct()
                .collect(Collectors.toList());

        List<String> sizeOptions = item.getItemStocks().stream()
                .map(ItemStock::getSize)
                .distinct()
                .collect(Collectors.toList());

        return new CreateItemResponse(
                item.getId(),
                item.getName(),
                item.getCategory().getName(),
                colorOptions.toString(),
                sizeOptions.toString(),
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

    //상품 상세 조회 ( 일단 누구나 조회 가능 먼저 구현 )
    @Override
    @Transactional(readOnly = true)
    public ItemResponse getOne(Long itemId) {
        //해당 상품이 없을 경우
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new BusinessException(NOT_FOUND_ITEM, " 존재하는 상품이 아닙니다"));
        // 이미지 URL 조회
        List<String> imgUrls = itemImageRepository.findByItem(item).stream()
                .map(AdminItemImageEntity::getImageUrl)
                .toList();
        //색상 및 옵션 생성
        List<ItemResponse.Option> options = item.getItemStocks().stream().map(itemStock -> new ItemResponse.Option(
                itemStock.getColor(),
                itemStock.getSize(),
                itemStock.getStockQty()
        )).collect(Collectors.toList());

        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getCategory().getId(),
                item.getPrice(),
                options,
                item.getDescription(),
                imgUrls
        );
    }


    @Override
    public ItemPageResponse categoryNameAll(Pageable pageable, String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new BusinessException(NOT_FOUND_CATEGORY));
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 16, pageable.getSort());
        Page<Item> items = itemRepository.findByCategory(pageRequest, category);
        if (items.isEmpty()) {
            throw new BusinessException(NOT_FOUND_ITEM, "해당 카테고리에 속한 상품이 없습니다");
        }

        List<ItemPageResponse.ItemList> itemLists = items.stream()
                .map(item -> new ItemPageResponse.ItemList(
                        item.getId()
                        , item.getName()
                        , item.getPrice()
                )).toList();

        long totalCount = items.getTotalElements();

        return new ItemPageResponse(items.getTotalPages(),
                totalCount,
                items.getNumber(),
                items.getSize(),
                itemLists);
    }


    private UserEntity getMember(User user) {
        return memberRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new BusinessException(NOT_FOUND_MEMBER));
    }
}
