package com.coderitesh.bloogingapp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	String resourceName;
	String fieldname;
	Integer field;
	public ResourceNotFoundException(String resourceName, String fieldname, Integer field) {
		super(String.format("%s not found with %s : %s", resourceName,fieldname,field));
		this.resourceName = resourceName;
		this.fieldname = fieldname;
		this.field = field;
	}
	
	

}
