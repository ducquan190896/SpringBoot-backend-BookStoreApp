package com.quan.bookstorepractice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.bookstorepractice.Entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    
    @Query(
        value = "select * from book where title = ?1",
        nativeQuery = true
    )
    Optional<Book> findByTitle(String title);
}
