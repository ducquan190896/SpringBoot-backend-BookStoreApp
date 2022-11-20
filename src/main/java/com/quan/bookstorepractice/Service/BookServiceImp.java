package com.quan.bookstorepractice.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.quan.bookstorepractice.Entity.Book;
import com.quan.bookstorepractice.Entity.Category;
import com.quan.bookstorepractice.Exception.EntityNotFoundException;
import com.quan.bookstorepractice.Repository.BookRepo;
import com.quan.bookstorepractice.Repository.CategoryRepo;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public void deleteBook(Long id) {
        Optional<Book> entity = bookRepo.findById(id);
        Book book = ischeck(entity, id);
        bookRepo.delete(book);
        
    }

    @Override
    public List<Book> getBookByQuery( Integer year, Double price) {
        List<Book> list = bookRepo.findAll();
        
        
        if(year !=null ) {
            list = list.stream().filter(bo -> Integer.valueOf(bo.getYear()) > year).collect(Collectors.toList());
        }
        if(price != null) {
            list = list.stream().filter(bo -> Double.valueOf(bo.getPrice()) > price).collect(Collectors.toList());
        }
        return list;

    }

    @Override
    public Book getBook(Long id) {
       
        Optional<Book> entity = bookRepo.findById(id);
        Book book = ischeck(entity, id);
        return book;
    }

    @Override
    public List<Book> getBooks() {
       PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("title").descending());
       Page<Book> page = bookRepo.findAll( pageRequest);
       return page.toList();
    }

    @Override
    public void saveBook(Book book, String category) {
       Optional<Book> entity = bookRepo.findByTitle(book.getTitle());
       if(entity.isPresent()) {
        throw new EntityNotFoundException("the book with title " + book.getTitle() + " already exists");
       }
       Optional<Category> categ = categoryRepo.findByName(category);
       if(!categ.isPresent()) {
        throw new EntityNotFoundException("the category with name " + category + " not exists");
       }
       Category catego = categ.get();
       book.setCategory(catego);
       bookRepo.save(book);
        
    }

    @Override
    public Book updateBook(Long id, Book book) {
       
        Optional<Book> entity = bookRepo.findById(id);
        Book book1 = ischeck(entity, id);
        book.setId(book1.getId());
        return bookRepo.save(book);
    }

    private Book ischeck(Optional<Book> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the book with id " + id + " not found");
    }
    
}
