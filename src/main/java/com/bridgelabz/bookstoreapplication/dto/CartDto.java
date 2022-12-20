package com.bridgelabz.bookstoreapplication.dto;

import com.bridgelabz.bookstoreapplication.model.BookModel;
import com.bridgelabz.bookstoreapplication.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDto {
    public UserModel userId;
    public BookModel bookId;
    public int quantity;

}
