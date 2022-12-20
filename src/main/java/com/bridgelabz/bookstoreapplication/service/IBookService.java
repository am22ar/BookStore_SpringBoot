package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.BookDto;
import com.bridgelabz.bookstoreapplication.model.BookModel;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface IBookService {
    public BookModel insertBook(BookDto bookDto);
    public List<BookModel> getAllBooks();
    public BookModel getBookById(Long bookId);
    public List<BookModel> getBookByName(String bookName);
    public BookModel updateById(Long bookId,BookDto bookDto);
    public List<BookModel> sortAscending();
    public List<BookModel> sortDescending();
    public Optional<BookModel> deleteById(Long bookId);
    public BookModel updateQuantity(Long bookId, BookDto bookDto);
}
