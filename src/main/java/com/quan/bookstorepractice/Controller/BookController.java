package com.quan.bookstorepractice.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quan.bookstorepractice.Entity.Book;
import com.quan.bookstorepractice.Service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
    
    @Autowired
    BookService bookService;

   @GetMapping("/all")
   public ResponseEntity<List<Book>> getAll() {
    return new ResponseEntity(bookService.getBooks(), HttpStatus.OK);
   }

   @GetMapping("/query")
   public ResponseEntity<List<Book>> getALlByQuery(@RequestParam(required = false) Integer year, @RequestParam(required = false) Double price) {
    return new ResponseEntity<List<Book>>(bookService.getBookByQuery(year, price), HttpStatus.OK);
   }

   @GetMapping("/id/{id}")
   public ResponseEntity<Book> getBook(@PathVariable Long id) {
    return new ResponseEntity<Book>(bookService.getBook(id), HttpStatus.OK);
   }

   @PostMapping("/category/{category}")
   public ResponseEntity<HttpStatus> saveBook(@Valid @RequestBody Book book, @PathVariable String category) {
    bookService.saveBook(book, category);
    return new ResponseEntity<HttpStatus>( HttpStatus.CREATED);
   }

   @PutMapping("/id/{id}")
   public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book, @PathVariable Long id) {
    return new ResponseEntity<Book>(bookService.updateBook(id, book), HttpStatus.ACCEPTED);
   }

   @DeleteMapping("/id/{id}")
   public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
