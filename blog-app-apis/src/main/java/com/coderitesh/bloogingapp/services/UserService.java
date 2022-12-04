package com.coderitesh.bloogingapp.services;

import java.util.List;

import com.coderitesh.bloogingapp.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user ,Integer id);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
	

}
