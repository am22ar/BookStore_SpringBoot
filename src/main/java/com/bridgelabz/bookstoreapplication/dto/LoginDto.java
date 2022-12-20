package com.bridgelabz.bookstoreapplication.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {
    @NotEmpty(message = "Please provide your Email")
    @Email
    private String email;
    @NotEmpty(message = "Please provide your Password")
    private String password;
}
