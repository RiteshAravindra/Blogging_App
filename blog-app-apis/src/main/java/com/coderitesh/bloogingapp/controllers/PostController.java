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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coderitesh.bloogingapp.entities.Post;
import com.coderitesh.bloogingapp.payloads.ApiResponce;
import com.coderitesh.bloogingapp.payloads.PostDto;
import com.coderitesh.bloogingapp.payloads.PostResponce;
import com.coderitesh.bloogingapp.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService ps;
		
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPosst
			(@RequestBody PostDto postdto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		
		PostDto createdpost = ps.createPost(postdto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createdpost,HttpStatus.CREATED);
		
		
		
	}
	
	//get posts by users 
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByuser(@PathVariable Integer userId){
		
		List<PostDto> retrivedlist = this.ps.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(retrivedlist,HttpStatus.OK);
		
	}
	// get posts by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostBycategory(@PathVariable Integer categoryId){
		
		List<PostDto> retrivedlist = this.ps.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(retrivedlist,HttpStatus.OK);
		
	}
	
	//get all post
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponce> getAllPostsList(@RequestParam(value="pageNumber",defaultValue = "0",required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = "5",required = false)Integer pageSize){
		
//		List<PostDto> list = this.ps.getAllPost(pageNumber,pageSize);
		PostResponce listofallPost = this.ps.getAllPost(pageNumber,pageSize);
		
		
		return new ResponseEntity<PostResponce>(listofallPost,HttpStatus.OK);
//		return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);
		
	}
	
	//get post by id
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto post =this.ps.getPostById(postId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
		
	}
	
	@DeleteMapping("posts/{postId}")
	public ResponseEntity<ApiResponce> deletePost(@PathVariable Integer postId){
		
		this.ps.deletepost(postId);
		
		return new ResponseEntity<ApiResponce>(new ApiResponce("Post is successfully  deleted",true),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto ,@PathVariable Integer postId){
		
		PostDto post =this.ps.updatePost(postdto, postId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.ACCEPTED);
	}
}
