package com.bridgelabz.bookstoreapplication.dto;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class OrderDto {
    @NotNull(message = "Quantity can not be null!")
    public int quantity;
    @NotNull(message = "Address can not be null!")
    public String address;
    @NotNull(message = "UserId can not be null")
    public Long userId;
    @NotNull(message = "BookId can not be null")
    public Long bookId;
    public boolean cancel=false;

}
