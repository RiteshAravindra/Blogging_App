package com.coderitesh.bloogingapp.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponce {
	
	private List<PostDto> content;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Long totalElement;

	private Integer totalPages;
	
	private boolean lastPage;
	
	
	
	
	

}