package com.bridgelabz.bookstoreapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UserDto {
    @NotEmpty(message = "Please Enter your firstName")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}\\s{0,}$")
    public String firstName;
    @NotEmpty(message = "Please Enter your lastName")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}\\s{0,}$")
    public String lastName;
    @Email
    @Column(unique = true)
    public String email;
    @NotEmpty(message = "Please Enter your password")
    public String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please Enter your DOB")
    @Past(message = "DOB should be ")
    public LocalDate dob;
    @NotEmpty(message = "Please Enter your address")
    public String address;

    public UserDto(String firstName, String lastName, String email, String password, LocalDate dob, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.address = address;
    }
}
