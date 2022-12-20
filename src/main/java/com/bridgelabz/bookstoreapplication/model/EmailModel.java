package com.bridgelabz.bookstoreapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmailModel {
    private String to;
    private String subject;
    private String body;
}
