package com.quan.bookstorepractice.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quan.bookstorepractice.Entity.Book;
import com.quan.bookstorepractice.Entity.Role;
import com.quan.bookstorepractice.Repository.RoleRepo;

import lombok.AllArgsConstructor;



public interface BookService {

    void saveBook(Book book, String category);
    void deleteBook(Long id);
    List<Book> getBooks();
    Book getBook(Long id);
    Book updateBook(Long id, Book book);
    List<Book> getBookByQuery( Integer year, Double price);
    
    
}
