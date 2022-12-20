package com.bridgelabz.bookstoreapplication.service;


import com.bridgelabz.bookstoreapplication.dto.ResponseDto;
import com.bridgelabz.bookstoreapplication.model.EmailModel;
import org.springframework.http.ResponseEntity;

public interface IEmailService {
    public ResponseEntity<ResponseDto> sendEmail(EmailModel emailModel);

}
