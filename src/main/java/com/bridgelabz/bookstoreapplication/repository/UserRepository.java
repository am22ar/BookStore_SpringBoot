package com.bridgelabz.bookstoreapplication.repository;

import com.bridgelabz.bookstoreapplication.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    @Query(value = "select * from user_model where email= :email",nativeQuery = true)
    public Optional<UserModel> getByEmail(String email);
}
