package com.harshit.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit.blogapi.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
