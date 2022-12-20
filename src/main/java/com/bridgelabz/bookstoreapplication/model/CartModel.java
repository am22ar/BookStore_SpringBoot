package com.bridgelabz.bookstoreapplication.model;

import com.bridgelabz.bookstoreapplication.dto.CartDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    @OneToOne
    @JoinColumn(name = "cart_user_id")
    private UserModel userId;
    @ManyToOne
    @JoinColumn(name = "cart_book_id")
    private BookModel bookId;
    private int quantity;

    public CartModel(CartDto cartDto) {
        this.userId = cartDto.userId;
        this.bookId = cartDto.bookId;
        this.quantity = cartDto.quantity;
    }
    public CartModel(CartDto cartDto,Long cartId ) {
        this.cartId = cartId;
        this.userId = cartDto.userId;
        this.bookId = cartDto.bookId;
        this.quantity = cartDto.quantity;
    }
}
