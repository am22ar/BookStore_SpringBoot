package com.bridgelabz.bookstoreapplication.repository;

import com.bridgelabz.bookstoreapplication.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartModel,Long> {
}
