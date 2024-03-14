package com.backend.superme.entity.view;


import com.backend.superme.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;


import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@ToString
@Table(name="carts")
public class Cart   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;


    // UserEntity를 매개변수로 받아 새로운 Cart 인스턴스를 생성하고 반환하는 메소드
    public static Cart createCart(UserEntity user) {
        Cart cart = new Cart();
        cart.setUser(user); // Cart와 UserEntity를 연결
        // 필요한 추가적인 초기화 작업 수행
        return cart;
    }


}
