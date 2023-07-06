package com.harshit.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit.blogapi.entities.Comment;


public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
}