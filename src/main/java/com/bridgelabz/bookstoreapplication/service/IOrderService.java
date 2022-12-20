package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.OrderDto;
import com.bridgelabz.bookstoreapplication.model.OrderModel;

import java.util.List;

public interface IOrderService {
    public OrderModel placeOrder(OrderDto orderDto);
    public List<OrderModel> getAllOrders();
    public OrderModel getOrderById(long orderId);
    public OrderModel updateRecordById(long orderId, OrderDto orderDto);
    public OrderModel cancelOrderById(long orderId, OrderDto orderDto);
}
