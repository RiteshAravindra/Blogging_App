package com.coderitesh.bloogingapp.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderitesh.bloogingapp.entities.User;
import com.coderitesh.bloogingapp.payloads.UserDto;
import com.coderitesh.bloogingapp.repositories.UserRepo;
import com.coderitesh.bloogingapp.services.UserService;
import com.coderitesh.bloogingapp.exceptions.*;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo ur;
	
	@Autowired
	private ModelMapper mm;

	@Override
	public UserDto createUser(UserDto userdto) {
		
		User user= this.dtoToUser(userdto);
		User saveduser=this.ur.save(user);
		
		return this.userToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userid) {
		User user =this.ur.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User","Id",userid));
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(user.getPassword());
		user.setAbout(user.getAbout());
		
		User updatedsave = this.ur.save(user);
		return this.userToDto(updatedsave);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user= this.ur.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users= this.ur.findAll();
		
		List<UserDto> userdtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user=this.ur.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		
		this.ur.delete(user);
		
	}
	
	private User dtoToUser(UserDto userDto) {
		
		
		User user =this.mm.map(userDto, User.class);
//		User user= new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
		
		
	}
	
	public UserDto userToDto(User user) {
		
		UserDto ud=this.mm.map(user, UserDto.class);
//		UserDto ud=new UserDto();
//		
//		ud.setId(user.getId());
//		ud.setName(user.getName());
//		ud.setEmail(user.getEmail());
//		ud.setPassword(user.getPassword());
//		ud.setAbout(user.getAbout());
		
		return ud;
		
	}
}
