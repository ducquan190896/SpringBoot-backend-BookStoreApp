package com.quan.bookstorepractice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.bookstorepractice.Entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    
    @Query(
        value = "select * from category where name = ?1",
        nativeQuery = true
    )
    Optional<Category> findByName(String name);
}
