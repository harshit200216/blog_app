package com.harshit.blogapi.controller;

import java.util.List;

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

import com.harshit.blogapi.payloads.ApiResponse;
import com.harshit.blogapi.payloads.CategoryDto;
import com.harshit.blogapi.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/newapi/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategories(@Valid @RequestBody CategoryDto categorydto)
	{
		CategoryDto createCategory = this.categoryService.createCategory(categorydto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategories(@Valid @RequestBody CategoryDto categorydto, @PathVariable Integer catId)
	{
		CategoryDto updatedCategory = this.categoryService.updateCategory(categorydto,catId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategories(@PathVariable Integer catId)
	{
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategories(@PathVariable Integer catId)
	{
		CategoryDto getCategory = this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(getCategory,HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> getAllCategory = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(getAllCategory,HttpStatus.OK);
	}

}
