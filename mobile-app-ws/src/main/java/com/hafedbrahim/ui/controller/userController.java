package com.hafedbrahim.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hafedbrahim.exceptions.UserServiceException;
import com.hafedbrahim.service.UserService;
import com.hafedbrahim.shared.dto.UserDto;
import com.hafedbrahim.ui.model.reponse.ErrorMessages;
import com.hafedbrahim.ui.model.reponse.OperationSatusModel;
import com.hafedbrahim.ui.model.reponse.UserRest;
import com.hafedbrahim.ui.model.request.UserDetailsRequestModel;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class userController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path="/{id}",
			produces = {MediaType.APPLICATION_XML_VALUE, 
						MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) {
		UserRest returnedValue = new UserRest();
		
		UserDto userDto = userService.getUserByUserId(id);
		
		BeanUtils.copyProperties(userDto, returnedValue);
		
		return returnedValue;
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, 
							 MediaType.APPLICATION_JSON_VALUE}, 
				produces = {MediaType.APPLICATION_XML_VALUE, 
							MediaType.APPLICATION_JSON_VALUE})	
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		
		if(userDetails.getEmail().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserRest returnedValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);	
		BeanUtils.copyProperties(createdUser, returnedValue);
		
		return returnedValue;
	}
	
	@PutMapping(path="/{id}",
				consumes = {MediaType.APPLICATION_XML_VALUE, 
			 				MediaType.APPLICATION_JSON_VALUE}, 
				produces = {MediaType.APPLICATION_XML_VALUE, 
							MediaType.APPLICATION_JSON_VALUE})	
	public UserRest updateUser(@PathVariable String id, 
							   @RequestBody UserDetailsRequestModel userDetails) 
									   throws Exception{
		if(userDetails.getEmail().isEmpty()) 
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserRest returnedValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updatedUser = userService.updateUser(id, userDto);	
		BeanUtils.copyProperties(updatedUser, returnedValue);
		
		return returnedValue;
	}
	
	@DeleteMapping(path="/{id}",
				   produces = {MediaType.APPLICATION_XML_VALUE, 
							   MediaType.APPLICATION_JSON_VALUE})	
	public OperationSatusModel deleteUser(@PathVariable String id) {
		OperationSatusModel returnedValue = new OperationSatusModel();
		returnedValue.setOperatinName("DELETE");
		
		userService.deleteUser(id);
		
		returnedValue.setOperationResult("SUCCESS");
		
		return returnedValue;
	}
	
} 
