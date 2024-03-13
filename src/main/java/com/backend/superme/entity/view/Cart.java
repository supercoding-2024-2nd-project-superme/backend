package com.backend.superme.entity.view;


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

    /* ToDo 유저 엔터티 추가후 연결
    @Column(name =user_id)
    private Long userId;
     */

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

      /* Todo 유저 연결 필요
    public static Cart createCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        return cart;
        }
     */


}
