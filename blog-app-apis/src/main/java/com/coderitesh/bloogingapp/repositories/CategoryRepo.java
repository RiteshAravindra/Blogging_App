package com.coderitesh.bloogingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderitesh.bloogingapp.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	

}
