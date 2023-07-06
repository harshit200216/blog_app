package com.harshit.blogapi.services;

import java.util.List;

import com.harshit.blogapi.payloads.CategoryDto;



public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	Void deleteCategory(Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getAllCategory();
}
