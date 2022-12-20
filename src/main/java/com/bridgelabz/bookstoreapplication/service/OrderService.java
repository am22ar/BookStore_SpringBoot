package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.OrderDto;
import com.bridgelabz.bookstoreapplication.exception.BookStoreException;
import com.bridgelabz.bookstoreapplication.model.BookModel;
import com.bridgelabz.bookstoreapplication.model.EmailModel;
import com.bridgelabz.bookstoreapplication.model.OrderModel;
import com.bridgelabz.bookstoreapplication.model.UserModel;
import com.bridgelabz.bookstoreapplication.repository.BookRepository;
import com.bridgelabz.bookstoreapplication.repository.CartRepository;
import com.bridgelabz.bookstoreapplication.repository.OrderRepository;
import com.bridgelabz.bookstoreapplication.repository.UserRepository;
import com.bridgelabz.bookstoreapplication.util.UserToken;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements  IOrderService{
    @Autowired
    IBookService bookService;
    @Autowired
    IUserService userService;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserToken userToken;
    @Autowired
    IEmailService iEmailService;
    Optional<UserModel> user;
    Optional<BookModel> book;
    @Override
    public OrderModel placeOrder(OrderDto orderDto){
        Optional<UserModel> user = userRepository.findById(orderDto.userId);
        Optional<BookModel> book = bookRepository.findById(orderDto.bookId);
        float totalPrice = book.get().getPrice()* orderDto.quantity;
        if (user.isPresent()) {
            OrderModel order = new OrderModel(user.get(),book.get(),totalPrice,orderDto.quantity,orderDto.address,orderDto.cancel);
            orderRepository.save(order);
            String token=userToken.createToken(order.getOrderId());
            EmailModel emailModel = new EmailModel(user.get().getEmail(),"Order Placed successfully ","Hii."+user.get().getFirstName());
            iEmailService.sendEmail(emailModel);
            return order;
        } else {
            throw new BookStoreException("User id or book id did not match! Please check and try again!");
        }
    }
    @Override
    public List<OrderModel> getAllOrders(){
        List<OrderModel> orderModelList = orderRepository.findAll();
        return orderModelList;
    }
    @Override
    public OrderModel getOrderById(long orderId){
        Optional<OrderModel> orderModel = orderRepository.findById(orderId);
        if(orderModel.isPresent())
            return orderModel.get();
        else
            throw new BookStoreException("OrderId: "+orderId+" not found..");
    }
    @Override
    public OrderModel updateRecordById(long orderId, OrderDto orderDto){
        Optional<UserModel> user = userRepository.findById(orderDto.userId);
        Optional<BookModel> book = bookRepository.findById(orderDto.bookId);
        float totalPrice = book.get().getPrice()* orderDto.quantity;
        if (user.isPresent()) {
            OrderModel order = new OrderModel(user.get(),book.get(),totalPrice,orderDto.quantity,orderDto.address,orderDto.cancel);
            order.setOrderId(orderId);
            return orderRepository.save(order);
        } else {
            throw new BookStoreException("User id or book id did not match! Please check and try again!");
        }
    }
    @Override
    public OrderModel cancelOrderById(long orderId, OrderDto orderDto) {
         Optional<OrderModel> orderModel = orderRepository.findById(orderId);
        if (orderModel.isPresent()) {
            OrderModel order = new OrderModel();
            order.setCancel(true);
            return orderRepository.save(order);
        } else {
            throw new BookStoreException("User id or book id did not match! Please check and try again!");
        }
    }
}
