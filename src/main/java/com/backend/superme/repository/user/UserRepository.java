package com.backend.superme.repository.user;

import com.backend.superme.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 이메일을 통해 이미 존재하는 사용자인지 확인하는 메소드
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}

//UserRepository 인터페이스는 UserEntity를 관리하기 위한 JPA 리포지토리입니다.
// JpaRepository를 상속함으로써,
// 기본적인 CRUD(Create, Read, Update, Delete) 연산과 페이징, 정렬 등의 다양한 데이터 접근 메소드를 사용할 수 있습니다.
// 여기에 추가된 두 개의 메소드는 사용자 관리와 인증 로직에 필수적인 기능을 제공합니다.

//boolean existsByEmail(String email)
//이 메소드는 특정 이메일을 가진 사용자가 데이터베이스에 존재하는지 여부를 검사합니다.
//이메일은 사용자 계정의 고유한 식별자로 작용하므로, 중복된 이메일을 가진 사용자가 없도록 하는 데 필요합니다.
//회원가입 시 이메일 중복 검사에 사용되며, true 또는 false를 반환하여 해당 이메일을 가진 사용자의 존재 여부를 나타냅니다.

//Optional<UserEntity> findByEmail(String email)
//이 메소드는 주어진 이메일 주소를 가진 UserEntity 객체를 조회합니다.
//Optional은 Java 8에 도입된 클래스로, 결과값이 null일 수 있는 상황에서 NullPointerException을 방지하도록 돕습니다.
//로그인 시나 사용자 정보 조회 시, 사용자를 식별하기 위해 사용됩니다.
// 입력된 이메일 주소에 해당하는 사용자가 데이터베이스에 존재한다면, 그 사용자의 UserEntity 객체를 감싸는 Optional 객체를 반환합니다.
// 사용자가 존재하지 않는 경우, 비어 있는 Optional 객체를 반환합니다.
//이 두 메소드를 통해, 애플리케이션은 사용자의 존재 여부를 체크하고, 사용자 정보를 조회하는 기능을 수행할 수 있습니다.
// 이는 사용자 인증, 회원가입, 사용자 정보 조회와 같은 기능 구현에 필수적인 데이터 접근 패턴입니다.