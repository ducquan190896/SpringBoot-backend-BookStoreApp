package com.quan.bookstorepractice.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quan.bookstorepractice.Entity.Category;
import com.quan.bookstorepractice.Repository.CategoryRepo;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> entity = categoryRepo.findById(id);
        Category category = isCheck(entity, id);
        categoryRepo.delete(category);
        
    }

    @Override
    public List<Category> getAlCategories() {
        
      return categoryRepo.findAll();
    }

    @Override
    public Category getCategory(String name) {
        
        Optional<Category> entity = categoryRepo.findByName(name);
        Category category = isCheck(entity, 404L);
        return category;
    }

    @Override
    public void saveCategory(Category category) {
        Optional<Category> entity = categoryRepo.findByName(category.getName());
        if(entity.isPresent()) {
            throw new EntityNotFoundException("the category with name " + category.getName() + " already exists");
        } 
            categoryRepo.save(category);
        
            // categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Long id, String name) {
        
        Optional<Category> entity = categoryRepo.findById(id);
        Category category = isCheck(entity, id);
        category.setName(name);
        return categoryRepo.save(category);
    }
    private Category isCheck(Optional<Category> entity, Long id) {
        if(entity.isPresent()) {
            return entity.get();
        }
        throw new EntityNotFoundException("the category with id " + id + " not found");
    }
}
