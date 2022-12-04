package com.coderitesh.bloogingapp.controllers;

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

import com.coderitesh.bloogingapp.payloads.ApiResponce;
import com.coderitesh.bloogingapp.payloads.CategoryDto;
import com.coderitesh.bloogingapp.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService cs;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @RequestBody CategoryDto category){
		
		CategoryDto createcdto =this.cs.createCategory(category);
		
		return new ResponseEntity<CategoryDto>(createcdto,HttpStatus.CREATED);
		
		
		
	}
	
	@PutMapping("/{catid}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto category,@PathVariable("catid") Integer id){
		
		CategoryDto updatedcdto =this.cs.updateCategory(category, id);
		
		return new ResponseEntity<CategoryDto>(updatedcdto,HttpStatus.CREATED);
		
		
		
	}
	
	@DeleteMapping("/{catid}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable("catid") Integer id){
		
		this.cs.deleteCategory(id);
		
		return new  ResponseEntity<ApiResponce>(new ApiResponce("Category deleted sucessfull",true),HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{catid}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catid") Integer id){
		
		CategoryDto cdtobyid = this.cs.getCatagory(id);
		
		return new  ResponseEntity<CategoryDto>(cdtobyid,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		
		List<CategoryDto> Listofcdto = this.cs.getCategoryDto();
		
		return new  ResponseEntity<List<CategoryDto>>(Listofcdto,HttpStatus.OK);
		
		
	}

}
