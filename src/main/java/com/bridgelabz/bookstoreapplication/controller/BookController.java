package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.dto.BookDto;
import com.bridgelabz.bookstoreapplication.dto.ResponseDto;
import com.bridgelabz.bookstoreapplication.exception.BookStoreException;
import com.bridgelabz.bookstoreapplication.model.BookModel;
import com.bridgelabz.bookstoreapplication.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore")
public class BookController {
    @Autowired
    IBookService iBookService;

    @PostMapping("/insertBook")
    public ResponseEntity<ResponseDto> insertBook(@Valid @RequestBody BookDto bookDto) throws BookStoreException {
        BookModel bookModel = iBookService.insertBook(bookDto);
        ResponseDto responseDto = new ResponseDto("New Book Added to Collection: "+bookModel.getBookName(),bookModel,null);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/getAllBooks")
    public ResponseEntity<ResponseDto> getAllBooks(){
        List<BookModel> bookModelList = iBookService.getAllBooks();
        ResponseDto responseDto = new ResponseDto("List of Books: ",bookModelList,null);
        return new  ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<ResponseDto> getBookById(@PathVariable Long bookId){
        BookModel bookModel = iBookService.getBookById(bookId);
        ResponseDto responseDto = new ResponseDto("Book with Id: "+bookModel.getBookId(),bookModel,null);
        return new  ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/getBookByName")
    public ResponseEntity<ResponseDto> getBookByName(@RequestParam String bookName){
        List<BookModel> bookModel = iBookService.getBookByName(bookName);
        ResponseDto responseDto = new ResponseDto(" Details of Book: "+bookName,bookModel,null);
        return new  ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteById/{bookId}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long bookId){
        Optional<BookModel> bookModel = iBookService.deleteById(bookId);
        ResponseDto responseDto = new ResponseDto(" Details of Deleted Book: "+bookId,bookModel,null);
        return new  ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/sortAscending")
    public ResponseEntity<ResponseDto> sortAscending(){
        List<BookModel> bookListAsc = iBookService.sortAscending();
        ResponseDto responseDto = new ResponseDto("List of Books from Low to High: ",bookListAsc,null);
        return new  ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/sortDescending")
    public ResponseEntity<ResponseDto> sortDescending(){
        List<BookModel> bookListDesc = iBookService.sortDescending();
        ResponseDto responseDto = new ResponseDto("List of Books from High to Low: ",bookListDesc,null);
        return new  ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }
    @PutMapping("/updateById/{bookId}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable Long bookId, @Valid @RequestBody BookDto bookDto) throws BookStoreException{
        BookModel bookModel = iBookService.updateById(bookId,bookDto);
        ResponseDto responseDto = new ResponseDto("Data updated of id: "+bookId,bookModel,null);
        return new  ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }
    @PutMapping("/updateQuantity/{bookId}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable Long bookId,@Valid @RequestBody BookDto bookDto) throws BookStoreException{
        BookModel bookModel = iBookService.updateQuantity(bookId,bookDto);
        ResponseDto responseDto = new ResponseDto("Data(Quantity) updated of id: "+bookId,bookModel,null);
        return new  ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);
    }
}
