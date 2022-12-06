package com.coderitesh.bloogingapp.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coderitesh.bloogingapp.entities.Category;
import com.coderitesh.bloogingapp.entities.Post;
import com.coderitesh.bloogingapp.entities.User;
import com.coderitesh.bloogingapp.exceptions.ResourceNotFoundException;
import com.coderitesh.bloogingapp.payloads.PostDto;
import com.coderitesh.bloogingapp.payloads.PostResponce;
import com.coderitesh.bloogingapp.repositories.CategoryRepo;
import com.coderitesh.bloogingapp.repositories.PostRepo;
import com.coderitesh.bloogingapp.repositories.UserRepo;
import com.coderitesh.bloogingapp.services.PostService;


@Service
public class PostServiceImpl implements PostService{
	
	
	@Autowired
	private PostRepo pr;
	
	@Autowired
	private ModelMapper mm;
	
	@Autowired
	private UserRepo ur;
	
	@Autowired
	private CategoryRepo cr;
	


	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		
		User user =ur.findById(userId).orElseThrow(()->new ResourceNotFoundException("Use", "UserId", userId));
		
		Category category=cr.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		
		Post post =this.mm.map(postDto, Post.class);
		
		post.setImageName("default.png");
		
		post.setAddedDate(new Date());
		
		post.setUser(user);
		
		post.setCategory(category);
		
		Post newpost= pr.save(post);
		
		PostDto pd = this.mm.map(newpost, PostDto.class);
		
		
		
		return pd;
	}
	
	
	
	@Override
	public PostDto updatePost(PostDto postdto, Integer postid) {
		
		Post post =this.pr.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postid));
		
		post.setTitle(postdto.getTitle());
		post.setImageName(postdto.getImageName());
		post.setContent(postdto.getContent());
		
		Post updatedpost = this.pr.save(post);
		
		return this.mm.map(updatedpost, PostDto.class);
		
		
	}

	@Override
	public void deletepost(Integer id) {
		
		Post post =this.pr.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", id));
		
		this.pr.delete(post);
		
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber,Integer pageSize) {
		
		
		Pageable p =PageRequest.of(pageNumber, pageSize);
		
		Page<Post> pagePost= this.pr.findAll(p);
		
		List<Post> liofpost=pagePost.getContent();
		
		List<PostDto>listofdtos= liofpost.stream().map((post)->this.mm.map(post,PostDto.class))
				.collect(Collectors.toList());
		
		PostResponce postResponce =new PostResponce();
		
		postResponce.setContent(listofdtos);
		
		postResponce.setPageNumber(pagePost.getNumber());
		
		postResponce.setPageSize(pagePost.getSize());
		
		postResponce.setTotalElement(pagePost.getTotalElements());
		
		postResponce.setTotalPages(pagePost.getTotalPages());
		
		postResponce.setLastPage(pagePost.isLast());
		
		return postResponce;
		
//		return listofdtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = this.pr.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		
		
		return this.mm.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		
		Category cat = this.cr.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		
		List<Post> listofposts=this.pr.findByCategory(cat);
		
		List<PostDto> listofpostdto = listofposts.stream().map((post-> this.mm.map(post, PostDto.class))).collect(Collectors.toList());
		
		return listofpostdto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User user = this.ur.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
		
		
		List<Post> userposts=this.pr.findByUser(user);
		
		List<PostDto> listofdto = userposts.stream().map((post)-> this.mm.map(post, PostDto.class)).
				collect(Collectors.toList());
		
		return listofdto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}


}
