package com.bridgelabz.bookstoreapplication.model;

import com.bridgelabz.bookstoreapplication.dto.BookDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private long price;
    private int quantity;

    public BookModel(BookDto bookDto) {
        this.bookName = bookDto.bookName;
        this.authorName = bookDto.authorName;
        this.bookDescription = bookDto.bookDescription;
        this.bookImg = bookDto.bookImg;
        this.price = bookDto.price;
        this.quantity = bookDto.quantity;
    }
    public BookModel(Long bookId,BookDto bookDto) {
        this.bookId = bookId;
        this.bookName = bookDto.bookName;
        this.authorName = bookDto.authorName;
        this.bookDescription = bookDto.bookDescription;
        this.bookImg = bookDto.bookImg;
        this.price = bookDto.price;
        this.quantity = bookDto.quantity;
    }
}
