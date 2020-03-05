package com.hafedbrahim.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hafedbrahim.service.UserService;
import com.hafedbrahim.shared.dto.UserDto;
import com.hafedbrahim.ui.model.reponse.UserRest;
import com.hafedbrahim.ui.model.request.UserDetailsRequestModel;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class userController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getUser() {
		return "get user was called";
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest returnedValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);	
		BeanUtils.copyProperties(createdUser, returnedValue);
		
		return returnedValue;
	}
	
	@PutMapping
	public String updateUser() {
		return "put user was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}
	
} 
