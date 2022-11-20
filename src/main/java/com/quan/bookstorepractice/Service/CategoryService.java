package com.quan.bookstorepractice.Service;

import java.util.List;

import com.quan.bookstorepractice.Entity.Category;

public interface CategoryService {
    void saveCategory(Category category);
    void deleteCategory(Long id);
    List<Category> getAlCategories();
    Category getCategory(String name);
    Category updateCategory(Long id, String name);
}
