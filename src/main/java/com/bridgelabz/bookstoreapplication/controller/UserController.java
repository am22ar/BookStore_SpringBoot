package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.dto.LoginDto;
import com.bridgelabz.bookstoreapplication.dto.ResponseDto;
import com.bridgelabz.bookstoreapplication.dto.UserDto;
import com.bridgelabz.bookstoreapplication.exception.BookStoreException;
import com.bridgelabz.bookstoreapplication.model.EmailModel;
import com.bridgelabz.bookstoreapplication.model.UserModel;
import com.bridgelabz.bookstoreapplication.service.IEmailService;
import com.bridgelabz.bookstoreapplication.service.IUserService;
import com.bridgelabz.bookstoreapplication.util.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/bookstore")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    IEmailService iEmailService;
    @Autowired
    UserToken userToken;

    @PostMapping("/registerUser")
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody UserDto userDto){
        UserModel newUser = userService.registerUser(userDto);
        String token = userToken.createToken(newUser.getUserId());
        EmailModel emailModel = new EmailModel(newUser.getEmail(),"Account Sign-up successfully "," Hello "+ newUser.getFirstName()
                + "\n Your Account has been created on 'Book Store'"+"\nSave this Token for future use: "+token);
        iEmailService.sendEmail(emailModel);
        ResponseDto responseDto = new ResponseDto("User Registered Successfully",newUser,token);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<ResponseDto> userLogin(@RequestBody LoginDto loginDto){
        String userModel = userService.userLogin(loginDto);
        ResponseDto responseDto = new ResponseDto("User Logged in Successful...",userService.userLogin(loginDto),null);
        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getRecordById/{userId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable(value = "userId") Long userId) throws BookStoreException {
        ResponseDto responseDto = new ResponseDto("User Data of Given ID:"+userId,userService.getById(userId),null);
        return new  ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @GetMapping("/getRecordByToken")
    public ResponseEntity<ResponseDto> getByToken(@RequestParam String token){
        UserModel userModel = userService.getByToken(token);
        ResponseDto responseDto = new ResponseDto("User Data of Given token:",userModel,null);
        return new  ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @GetMapping("/getAllRecords")
    public ResponseEntity<ResponseDto> getAll() throws BookStoreException{
        List<UserModel> userModelList = userService.getAll();
        ResponseDto responseDto = new ResponseDto("All the Records: ",userModelList,null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @GetMapping("/getRecordByEmail")
    public ResponseEntity<ResponseDto> getByEmail(@RequestParam String email){
        UserModel userModel = userService.getByEmail(email);
        ResponseDto responseDto = new ResponseDto("Data of Given Email: ",userModel,null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PutMapping("/updateByEmail")
    public ResponseEntity<ResponseDto> updateById(@RequestParam String email,@Valid @RequestBody UserDto userDto) throws  BookStoreException{
        UserModel userModel = userService.updateByEmail(email,userDto);
        EmailModel emailModel = new EmailModel(userModel.getEmail(),"Data Updated Using Email.",userModel.getEmail());
        iEmailService.sendEmail(emailModel);
        ResponseDto responseDto = new ResponseDto("Updated data of Given Email: ",userModel,null);
        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody UserDto userDto, @RequestParam String email){
        UserModel userModel = userService.changePassword(userDto,email);
        EmailModel emailModel = new EmailModel(userModel.getEmail(),"Password Updated..",
                "Hi "+userModel.getFirstName()+" Your Password for 'Book Store' has been changed successfully..");
        iEmailService.sendEmail(emailModel);
        ResponseDto responseDto = new ResponseDto("Password Changed ...",userModel,null);
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }
}
