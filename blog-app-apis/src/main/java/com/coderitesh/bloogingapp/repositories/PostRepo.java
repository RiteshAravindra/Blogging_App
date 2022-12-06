package com.coderitesh.bloogingapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderitesh.bloogingapp.entities.Category;
import com.coderitesh.bloogingapp.entities.Post;
import com.coderitesh.bloogingapp.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	

}
