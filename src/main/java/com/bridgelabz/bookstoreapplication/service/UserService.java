package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.LoginDto;
import com.bridgelabz.bookstoreapplication.dto.UserDto;
import com.bridgelabz.bookstoreapplication.exception.BookStoreException;
import com.bridgelabz.bookstoreapplication.model.UserModel;
import com.bridgelabz.bookstoreapplication.repository.UserRepository;
import com.bridgelabz.bookstoreapplication.util.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService{

    List<UserModel> userModelList = new ArrayList<>();
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserToken userToken;

    @Override
    public UserModel registerUser(UserDto userDto){
        Optional<UserModel> newUser = userRepository.getByEmail(userDto.getEmail());
        //checks if user has already registered using this email
        if(newUser.isPresent()){
            throw new BookStoreException("User Already exists...");
        }else {
            //if email doesn't exists then new user will register
            UserModel userModel = new UserModel(userDto);
            return userRepository.save(userModel);
        }
    }

    @Override
    public String  userLogin(LoginDto logindto) {
        Optional<UserModel> newUser = userRepository.getByEmail(logindto.getEmail());
        if (logindto.getEmail().equals(newUser.get().getEmail()) &&
                logindto.getPassword().equals(newUser.get().getPassword())) {
            log.info("SuccessFully Logged In");
            return "SuccessFully Logged In";
        } else {
            throw new BookStoreException("User doesn't exists");
        }
    }

    @Override
    public Object userLoginCheck(LoginDto loginDto){
        Optional<UserModel> newUser = userRepository.getByEmail(loginDto.getEmail());
        if(loginDto.getEmail().equals(newUser.get().getEmail()) &&
                loginDto.getPassword().equals(newUser.get().getPassword())){
            return newUser.get();
        }
        else {
            throw new BookStoreException("Sorry!! Either Username or password is incorrect");
        }
    }
    @Override
    public UserModel getById(Long id){
        Optional<UserModel> userModel= userRepository.findById(id);
        if(userModel.isPresent()){
            return userModel.get();
        }else {
            throw new BookStoreException("Id not found...");
        }
    }

    @Override
    public UserModel getByToken(String token){
        Long userId = userToken.decodeToken(token);
        Optional<UserModel> userModel = userRepository.findById(userId);
        if (userModel.isPresent()){
            return userModel.get();
        }else {
            throw new BookStoreException("Token doesn't Exists...");
        }
    }
    @Override
    public List<UserModel> getAll() {
        List<UserModel> userModelList = userRepository.findAll();
        return userModelList;
    }
    @Override
    public UserModel getByEmail(String email){
        Optional<UserModel> userModel = userRepository.getByEmail(email);
        if(userModel.isPresent())
        {
            return userModel.get();
        }
        else {
            throw new BookStoreException("Email is not available");
        }
    }

    @Override
    public UserModel updateByEmail(String email,UserDto userDto){
        if (userRepository.getByEmail(email).isPresent()) {
            UserModel newAdd = new UserModel(email,userDto);
            UserModel saved = userRepository.save(newAdd);
            return saved;
        } else {
            throw new BookStoreException("id not found");
        }
    }

    @Override
    public UserModel changePassword(UserDto userDto, String email) {
        Optional<UserModel> user = userRepository.getByEmail(email);
        if(user != null){
            user.get().setPassword(userDto.getPassword());
            return userRepository.save(user.get());
        }else {
            throw new BookStoreException("Sorry we could not find your email: "+email);
        }
    }

}
