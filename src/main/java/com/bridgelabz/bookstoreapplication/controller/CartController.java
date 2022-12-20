package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.dto.CartDto;
import com.bridgelabz.bookstoreapplication.dto.ResponseDto;
import com.bridgelabz.bookstoreapplication.model.CartModel;
import com.bridgelabz.bookstoreapplication.service.CartService;
import com.bridgelabz.bookstoreapplication.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bookstore")
public class CartController {
    @Autowired
    ICartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDto> addToCart( @RequestBody CartDto cartDto){
        CartModel cartModel = cartService.addToCart(cartDto);
        ResponseDto responseDto = new ResponseDto("Book Added to Cart By User: ","Successfully added to cart...",null);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/getAllData")
    public ResponseEntity<ResponseDto> getAll(){
        List<CartModel> cartModelList = cartService.getAll();
        ResponseDto responseDto = new ResponseDto("All the Data in Cart: ",cartModelList,null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long cartId){
        CartModel cartModel = cartService.getById(cartId);
        ResponseDto responseDto = new ResponseDto("Cart Data of ID: '"+cartId+"'",cartModel,null);
        return new ResponseEntity<>(responseDto,HttpStatus.FOUND);
    }
    @PutMapping("/updateCartById/{cartId}")
    public ResponseEntity<ResponseDto> updateCartById(@RequestBody CartDto cartDto , @PathVariable Long cartId){
        CartModel cartModel = cartService.updateCartByID(cartDto,cartId);
        ResponseDto responseDto = new ResponseDto("Record Updated Successfully for ID:"+cartId,cartModel,null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @PutMapping("/updateCartQuantity/{cartId}")
    public ResponseEntity<ResponseDto> updateCartQuantity(@RequestBody CartDto cartDto, @PathVariable Long cartId,@RequestParam int quantity){
        CartModel cartModel = cartService.updateCartQuantity(cartDto,cartId,quantity);
        ResponseDto responseDto = new ResponseDto("Quantity Updated Successfully of :"+cartId,"Updated Quantity: "+cartModel.getQuantity(),null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteCartId/{cartId}")
    public ResponseEntity<ResponseDto> deleteCartId(@PathVariable Long cartId){
         cartService.deleteCartId(cartId);
        ResponseDto responseDto = new ResponseDto("Data Deleted of Id :"+cartId,"Data Deleted Successfully...",null);
        return new ResponseEntity<>(responseDto,HttpStatus.GONE);
    }
}
