package com.hafedbrahim.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hafedbrahim.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
}
