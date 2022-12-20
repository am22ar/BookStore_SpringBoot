package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.CartDto;
import com.bridgelabz.bookstoreapplication.exception.BookStoreException;
import com.bridgelabz.bookstoreapplication.model.BookModel;
import com.bridgelabz.bookstoreapplication.model.CartModel;
import com.bridgelabz.bookstoreapplication.model.UserModel;
import com.bridgelabz.bookstoreapplication.repository.BookRepository;
import com.bridgelabz.bookstoreapplication.repository.CartRepository;
import com.bridgelabz.bookstoreapplication.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    CartRepository cartRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public CartModel addToCart(CartDto cartDto){
        Optional<UserModel> userModel = userRepository.findById(cartDto.userId.getUserId());
        Optional<BookModel> bookModel = bookRepository.findById(cartDto.bookId.getBookId());
       // Optional<BookModel> checkBookId = bookRepository.findById(cartDto.bookId.getBookId());
        int bookQuantity = bookModel.get().getQuantity();
        if(userModel.isPresent() && bookModel.isPresent()){
            if(cartDto.quantity <= bookQuantity) {
                CartModel cartModel = new CartModel(cartDto);
                return cartRepository.save(cartModel);
            }else {
                throw new BookStoreException("Sorry! "+cartDto.quantity+" Quantity is/are not available. Please add fewer than "+bookQuantity+" quantity and try again!");
            }
        }else {
            throw new BookStoreException("Either UserId or BookId is not found");
        }
    }
    @Override
    public List<CartModel> getAll() {
        List<CartModel> cartModelList = cartRepository.findAll();
        return cartModelList;
    }

    @Override
    public CartModel getById(Long cartId) {
        Optional<CartModel> cartModel = cartRepository.findById(cartId);
        if(cartModel.isPresent()){
            return cartModel.get();
        }else {
            throw new BookStoreException("Cart with Id: '"+cartId+"' not found");
        }
    }
    @Override
    public CartModel updateCartByID(CartDto cartDto, Long cartId){
        Optional<UserModel> checkUserId = userRepository.findById(cartDto.userId.getUserId());
        Optional<BookModel> checkBookId = bookRepository.findById(cartDto.bookId.getBookId());
        Optional<CartModel> checkCartId = cartRepository.findById(cartId);
        if(checkCartId.isPresent()){
            if(checkUserId.isPresent() && checkBookId.isPresent()){
                CartModel cartModel = new CartModel(cartDto);
                cartModel.setCartId(cartId);
                return cartRepository.save(cartModel);
            }else {
                throw new BookStoreException("Either UserId or BookId is not found! Update Failed");
            }
        }else {
            throw new BookStoreException("CartId: "+cartId+" not found..");
        }
    }
    @Override

    public CartModel updateCartQuantity(CartDto cartDto,Long cartId,int quantity){
        Optional<CartModel> checkCartId = cartRepository.findById(cartId);
        Optional<BookModel> checkBookId = bookRepository.findById(cartDto.bookId.getBookId());
        int bookQuantity = checkBookId.get().getQuantity();
        if (checkCartId.isPresent()) {
            if (quantity < bookQuantity) {
                CartModel cartModel = new CartModel(cartDto);
                cartModel.setCartId(cartId);
                cartModel.setQuantity(quantity);
                cartModel.setUserId(checkCartId.get().getUserId());
                cartModel.setBookId(checkCartId.get().getBookId());
                return cartRepository.save(cartModel);
            } else {
                throw new BookStoreException("Requested Quantity: " + quantity + " available Quantity: " + bookQuantity);
            }
        }else {
            throw new BookStoreException("CartId: "+checkCartId+" not found");
        }
    }
    @Override
    public String deleteCartId(long cartId){
        Optional<CartModel> cartData = cartRepository.findById(cartId);
        if(cartData.isPresent()){
            cartRepository.deleteById(cartId);
        }else {
            throw new BookStoreException("CartId: "+cartId+" not found");
        }
        return "CartId"+cartId+" deleted Successfully..";
    }
}
