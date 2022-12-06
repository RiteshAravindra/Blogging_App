package com.coderitesh.bloogingapp.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.message.Message;
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
import org.springframework.web.bind.annotation.RestController;

import com.coderitesh.bloogingapp.exceptions.ResourceNotFoundException;
import com.coderitesh.bloogingapp.payloads.ApiResponce;
import com.coderitesh.bloogingapp.payloads.UserDto;
import com.coderitesh.bloogingapp.services.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userservice ;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto){
		
		UserDto createuserdto =this.userservice.createUser(userdto);
		
		return new ResponseEntity<>(createuserdto,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto,@PathVariable("userid") Integer id){
		
		UserDto updateduserdto =this.userservice.updateUser(userdto,id);
		
		return new ResponseEntity<>(updateduserdto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable("userId") Integer id){
		 this.userservice.deleteUser(id);
//		return new ResponseEntity<?>(Map.of("message","User deleted sucessfully"),HttpStatus.OK);
		 return new ResponseEntity<ApiResponce>(new ApiResponce("User deleted sucessfull",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> gellAllUsers(){
		List<UserDto> listofusers= this.userservice.getAllUsers();
		return new ResponseEntity<List<UserDto>>(listofusers,HttpStatus.OK);
		
	}
	
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> gelUser(@PathVariable Integer userid){
		UserDto user= this.userservice.getUserById(userid);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
		
	
}
}
