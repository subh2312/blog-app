package com.subhankar.blogappbackend.services;

import com.subhankar.blogappbackend.dto.CategoryDto;
import com.subhankar.blogappbackend.entities.Category;
import com.subhankar.blogappbackend.exceptions.ResourceNotFoundException;
import com.subhankar.blogappbackend.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryRepository.save(modelMapper.map(categoryDto, Category.class)), CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        return modelMapper.map(categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id)),CategoryDto.class);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","Id",id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        return modelMapper.map(categoryRepository.save(category),CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id)));

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }
}
