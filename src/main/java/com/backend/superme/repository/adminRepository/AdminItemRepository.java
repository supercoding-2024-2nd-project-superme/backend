package com.backend.superme.repository.adminRepository;

import com.backend.superme.entity.user.UserEntity;
import com.backend.superme.entity.view.Category;
import com.backend.superme.entity.view.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface AdminItemRepository extends JpaRepository<Item, Long> {

    //아이템 이름 조회
    Optional<Item> findByItemName(String itemName);

//    //특정 회원이 등록한 아이템을 페이지네이션된 형태로 조회합니다.
//    Page<Item> findByMember(Pageable pageable, UserEntity member);
//
//    //특정 카테고리에 속한 아이템을 페이지네이션된 형태로 조회
//    Page<Item> findByCategory(Pageable pageable, Category category);
//
//    //특정 회원이 등록한 모든 아이템을 조회합니다.
//    Page<Item> findALlByMember(UserEntity user);

}
