package com.bridgelabz.bookstoreapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class BookDto {

    @NotEmpty(message = "Please Enter a BookName")
    public String bookName;
    @NotEmpty(message = "Please Enter a Book AuthorName")
    public String authorName;
    @NotEmpty(message = "Please Enter a Book Description")
    public String bookDescription;
    @NotEmpty(message = "Please Enter a Book Image")
    public String bookImg;
    @NotNull(message = "Please Enter a Book Price")
    public long price;
    @NotNull(message = "Please Enter a Book Quantity")
    @Min(value = 1)
    @Max(value = 500)
    public int quantity;

}
