package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.BookDto;
import com.bridgelabz.bookstoreapplication.exception.BookStoreException;
import com.bridgelabz.bookstoreapplication.model.BookModel;
import com.bridgelabz.bookstoreapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    List<BookModel> bookModelList = new ArrayList<>();
    @Autowired
    BookRepository bookRepository;

    @Override
    public BookModel insertBook(BookDto bookDto) {
            BookModel bookModel = new BookModel(bookDto);
            return bookRepository.save(bookModel);
    }
    @Override
    public List<BookModel> getAllBooks() {
        bookModelList = bookRepository.findAll();
        return bookModelList;
    }

    @Override
    public BookModel getBookById(Long bookId) {
        Optional<BookModel> getBookId = bookRepository.findById(bookId);
        if(getBookId == null){
            throw new BookStoreException("Book with id: '"+getBookId+"' not found");
        }
        else {
            return getBookId.get();
        }
    }
    @Override
    public List<BookModel> getBookByName(String bookName) {
        List<BookModel> bookModel = bookRepository.findByBookName(bookName);
        if(bookModel != null){
            return bookModel;
        }else {
            throw new BookStoreException("Book with this name: '"+bookName+"' not found");
        }
    }
    @Override
    public BookModel updateById(Long bookId, BookDto bookDto){
        Optional<BookModel> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new BookStoreException("Book Record doesn't exists for this Id: '"+bookId+"'");
        } else {
            BookModel newBook = new BookModel(bookId,bookDto);
            return bookRepository.save(newBook);
        }
    }
    @Override
    public List<BookModel> sortAscending() {
        bookModelList = bookRepository.sortAscending();
        return bookModelList;
    }
    @Override
    public List<BookModel> sortDescending() {
        bookModelList = bookRepository.sortDescending();
        return bookModelList;
    }
    @Override
    public Optional<BookModel> deleteById(Long bookId) {
        Optional<BookModel> bookModel = bookRepository.findById(bookId);
        if(bookModel.isPresent()){
             bookRepository.deleteById(bookId);
             return bookModel;
        }else {
            throw new BookStoreException("Book with Id: '"+bookId+"' doesn't exists");
        }
    }
    @Override
    public BookModel updateQuantity(Long bookId, BookDto bookDto) {
        BookModel bookModel = bookRepository.findById(bookId).get();
        if (bookModel != null){
            bookModel.setQuantity(bookDto.getQuantity());
            return bookRepository.save(bookModel);
        }else {
            throw new BookStoreException("Quantity not updated for this Id: '"+bookId+"' ");
        }
    }


}
