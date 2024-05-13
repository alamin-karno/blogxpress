package com.alaminkarno.blogxpress.services.impl;

import com.alaminkarno.blogxpress.entities.Category;
import com.alaminkarno.blogxpress.entities.User;
import com.alaminkarno.blogxpress.exceptions.ResourceNotFoundException;
import com.alaminkarno.blogxpress.payloads.CategoryDto;
import com.alaminkarno.blogxpress.payloads.UserDto;
import com.alaminkarno.blogxpress.repositories.CategoryRepository;
import com.alaminkarno.blogxpress.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Category savedCategory = this.categoryRepository.save(category);
        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));

        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());

        Category updatedCategory = this.categoryRepository.save(category);
        return this.categoryToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map(this::categoryToDto).collect(Collectors.toList());

    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        return this.categoryToDto(category);
    }

    private Category dtoToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto,Category.class);
    }

    private CategoryDto categoryToDto(Category category){
        return this.modelMapper.map(category,CategoryDto.class);
    }
}
