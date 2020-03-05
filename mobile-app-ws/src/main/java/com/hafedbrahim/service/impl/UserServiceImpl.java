package com.hafedbrahim.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hafedbrahim.UserRepository;
import com.hafedbrahim.io.entity.UserEntity;
import com.hafedbrahim.service.UserService;
import com.hafedbrahim.shared.Utils;
import com.hafedbrahim.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder; 

	@Override
	public UserDto createUser(UserDto user) {

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String userId = utils.generateUserId(30);
		userEntity.setUserId(userId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode((user.getPassword())));
		
		UserEntity storedUserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
