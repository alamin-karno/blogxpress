package com.alaminkarno.blogxpress.services;


import com.alaminkarno.blogxpress.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    void deleteCategory(Integer categoryId);

    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(Integer categoryId);

}
