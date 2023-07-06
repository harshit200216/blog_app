package com.harshit.blogapi.services;

import java.util.List;

import com.harshit.blogapi.payloads.UserDto;



public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userid);
	
	UserDto getUserById(Integer userid);
	
	List<UserDto> getAllusers();
	
	void deleteUser(Integer userid);
}
