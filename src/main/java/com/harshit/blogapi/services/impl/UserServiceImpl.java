package com.harshit.blogapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshit.blogapi.entities.User;
import com.harshit.blogapi.exceptions.ResourceNotFoundException;
import com.harshit.blogapi.payloads.UserDto;
import com.harshit.blogapi.repositories.*;
import com.harshit.blogapi.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userid) {
		
		User user =this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User", " id ", userid));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		return this.userToDto(updateUser);
	}

	@Override
	public UserDto getUserById(Integer userid) {

		User user =this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User", " id ", userid));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllusers() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userid) {
		User user =this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User", " id ", userid));
		this.userRepo.delete(user);
	}
	
	
	
	private User dtoToUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		
//		User user = new User();
//		user.setName(userDto.getName());
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
		
	}
	private UserDto userToDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		UserDto userDto = new UserDto();
//		userDto.setName(user.getName());
//		userDto.setId(user.getId());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		
		return userDto;
		
	}
}
