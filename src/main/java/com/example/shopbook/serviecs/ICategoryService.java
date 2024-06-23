package com.example.shopbook.serviecs;

import com.example.shopbook.dtos.CategoryDTO;
import com.example.shopbook.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO category);
    Category getCategoryId(long id);
    List<Category> getAllCategories();
    Category updateCategory(long categoryId, CategoryDTO category);
    void deleteCategory(long id);
}
