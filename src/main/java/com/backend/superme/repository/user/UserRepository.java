package com.backend.superme.repository.user;

import com.backend.superme.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 이메일을 통해 이미 존재하는 사용자인지 확인하는 메소드
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
