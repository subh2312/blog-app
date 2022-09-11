package com.subhankar.blogappbackend.controllers;

import com.subhankar.blogappbackend.dto.CategoryDto;
import com.subhankar.blogappbackend.payloads.ApiResponse;
import com.subhankar.blogappbackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("id") Integer id){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id){
        categoryService.deleteCategory(id);
        return new  ResponseEntity(new ApiResponse("Category deleted Successfully",true,HttpStatus.OK.value()),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }
}
