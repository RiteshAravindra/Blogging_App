package com.coderitesh.bloogingapp.payloads;

import java.util.Date;

import com.coderitesh.bloogingapp.entities.Category;
import com.coderitesh.bloogingapp.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	private Integer id;
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	
	
	
	
	
	

}
