package com.harshit.blogapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit.blogapi.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
