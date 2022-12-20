package com.bridgelabz.bookstoreapplication.model;

import com.bridgelabz.bookstoreapplication.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class UserModel {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
    private String address;

    public UserModel(UserDto userDto) {
        this.firstName = userDto.firstName;
        this.lastName = userDto.lastName;
        this.email = userDto.email;
        this.password = userDto.password;
        this.dob = userDto.dob;
        this.address = userDto.address;
    }
    public UserModel(String email,UserDto userDto) {
        this.email = email;
        this.firstName = userDto.firstName;
        this.lastName = userDto.lastName;
        this.password = userDto.password;
        this.dob = userDto.dob;
        this.address = userDto.address;
    }


}
