package com.backend.superme.entity.view;

import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="carts")
public class Cart   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    //ToDo: 반복된 부분 extends 작업 확인
    @Column(name = "created_at", updatable = false) // 수정 불가능한 필드로 설정
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default // 롬복 빌더 기본값 설정
    private Date createdAt = new Date(); // 생성 시 현재 시간으로 초기화

    //ToDo: 반복된 부분 extends 작업 확인
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date updatedAt = new Date(); // 생성 시 현재 시간으로 초기화
}
