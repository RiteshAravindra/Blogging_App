package com.coderitesh.bloogingapp.services;

import java.util.List;

import com.coderitesh.bloogingapp.entities.Post;
import com.coderitesh.bloogingapp.payloads.PostDto;
import com.coderitesh.bloogingapp.payloads.PostResponce;

public interface PostService {
	
	PostDto createPost(PostDto postDto ,Integer userId, Integer categoryid);
	
	PostDto updatePost(PostDto postdto,Integer postid);
	
	void deletepost(Integer id);
	
	
	//get all post
//	List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
	
	PostResponce getAllPost(Integer pageNumber,Integer pageSize);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search by keyword
	List<PostDto> searchPosts(String keyword);
	
	
	
	

}
