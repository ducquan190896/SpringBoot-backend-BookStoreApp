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
import org.springframework.web.bind.annotation.RestController;

import com.quan.bookstorepractice.Entity.Category;
import com.quan.bookstorepractice.Service.CategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired 
    CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getALl() {
        return new ResponseEntity<>(categoryService.getAlCategories(), HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategory(@PathVariable String name) {
        return new ResponseEntity<>(categoryService.getCategory(name), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> saveCategory(@Valid @RequestBody Category category) {
        categoryService.saveCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}/name/{name}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @PathVariable String name) {
        return new ResponseEntity<Category>(categoryService.updateCategory(id, name), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
