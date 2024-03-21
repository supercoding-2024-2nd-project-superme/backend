package com.backend.superme.repository.adminRepository;

import com.backend.superme.entity.ItemImgEntity.AdminItemImageEntity;
import com.backend.superme.entity.view.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminItemImageRepository extends JpaRepository<AdminItemImageEntity, Long> {

    List<AdminItemImageEntity> findByItem(Item adminItemImageEntity);

    AdminItemImageEntity findByImageUrl(String firstImgUrl);
}
