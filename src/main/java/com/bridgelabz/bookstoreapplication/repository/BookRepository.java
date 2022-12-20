package com.bridgelabz.bookstoreapplication.repository;

import com.bridgelabz.bookstoreapplication.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Long> {

    @Query(value = "select * from book_model where book_name=:bookname",nativeQuery = true)
    List<BookModel> findByBookName(String bookname);
    @Query(value = " select * from book_model order by price asc;",nativeQuery = true)
    List<BookModel> sortAscending();
    @Query(value = " select * from book_model order by price desc;",nativeQuery = true)
    List<BookModel> sortDescending();

}
