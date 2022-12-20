package com.bridgelabz.bookstoreapplication.repository;

import com.bridgelabz.bookstoreapplication.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel,Long> {
}
