package com.coderitesh.bloogingapp.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderitesh.bloogingapp.entities.Category;
import com.coderitesh.bloogingapp.exceptions.ResourceNotFoundException;
import com.coderitesh.bloogingapp.payloads.CategoryDto;
import com.coderitesh.bloogingapp.repositories.CategoryRepo;
import com.coderitesh.bloogingapp.services.CategoryService;


@Service
public class CategoryServiceimpl implements CategoryService{
	
	
	@Autowired
	private CategoryRepo cr;
	
	@Autowired
	private ModelMapper mm;
	

	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		Category c =mm.map(categorydto, Category.class);
		
		
		
		Category addedcategory = this.cr.save(c);
		
		return this.mm.map(addedcategory, CategoryDto.class);
		
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, Integer id) {
		
		Category c =this.cr.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "category id", id));
		
		c.setCategoryTitle(categorydto.getCategoryTitle());
		c.setCategoryDescription(categorydto.getCategoryDescription());
		Category updatedcat =this.cr.save(c);
		
		CategoryDto cdto =this.mm.map(updatedcat, CategoryDto.class);
		return cdto;
	}

	@Override
	public void deleteCategory(Integer categoryid) {
		
	 Category cat=this.cr.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryid));
	 this.cr.delete(cat);
	}

	@Override
	public CategoryDto getCatagory(Integer categoryId) {
		
		Category cat=this.cr.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "CategoryId", categoryId));
		
		CategoryDto cdto=this.mm.map(cat, CategoryDto.class);
		
		
		
		return cdto;
	}

	@Override
	public List<CategoryDto> getCategoryDto() {
		
		
		List<Category> categories =this.cr.findAll();
		
		List<CategoryDto> listofcatdto =categories.stream().map((cat)->this.mm.map(cat,CategoryDto.class )).collect(Collectors.toList());
		
		return listofcatdto;
	}
	
	

}
