package com.coderitesh.bloogingapp.services;

import java.util.List;

import com.coderitesh.bloogingapp.payloads.CategoryDto;

public interface CategoryService {
	
		CategoryDto createCategory(CategoryDto categorydto);
		
		CategoryDto updateCategory(CategoryDto categorydto,Integer id);
		
		void deleteCategory(Integer categoryid);
		
		CategoryDto getCatagory(Integer categoryId);
		
		List<CategoryDto> getCategoryDto();
		
		

}
