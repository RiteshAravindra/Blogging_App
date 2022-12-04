package com.coderitesh.bloogingapp.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	
	
	private String categoryTitle;
	

	private String categoryDescription;

}


//private Integer categoryId;

//@Column(name= "title",length=100,nullable=false)
//private String categoryTitle;

//@Column(name="description")
//categoryDescription;