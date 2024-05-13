package com.alaminkarno.blogxpress.controllers;

import com.alaminkarno.blogxpress.payloads.ApiResponse;
import com.alaminkarno.blogxpress.payloads.CategoryDto;
import com.alaminkarno.blogxpress.payloads.UserDto;
import com.alaminkarno.blogxpress.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    // POST: CREATE CATEGORY
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto newCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(newCategoryDto, HttpStatus.CREATED);
    }

    // PUT: UPDATE CATEGORY
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        CategoryDto updatedCategoryDto = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    // DELETE: DELETE CATEGORY
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return  ResponseEntity.ok(new ApiResponse("Category deleted successfully",true));
    }

    // GET: GET ALL CATEGORIES
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categoryDtoList = this.categoryService.getCategories();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    // GET: GET CATEGORY BY ID
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
        CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

}
