package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.LoginDto;
import com.bridgelabz.bookstoreapplication.dto.UserDto;
import com.bridgelabz.bookstoreapplication.model.UserModel;

import java.util.List;

public interface IUserService {
    public UserModel registerUser(UserDto userDto);
    public String  userLogin(LoginDto logindto);
    public UserModel getById(Long id);
    public UserModel getByToken(String token);
    public List<UserModel> getAll();
    public UserModel getByEmail(String email);
    public UserModel updateByEmail(String email,UserDto userDto);
    public UserModel changePassword(UserDto userDto, String email);
    public Object userLoginCheck(LoginDto loginDto);
}
