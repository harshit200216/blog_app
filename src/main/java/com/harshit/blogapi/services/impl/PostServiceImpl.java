package com.harshit.blogapi.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.harshit.blogapi.entities.User;
import com.harshit.blogapi.exceptions.ResourceNotFoundException;
import com.harshit.blogapi.payloads.PostDto;
import com.harshit.blogapi.payloads.PostResponse;
import com.harshit.blogapi.repositories.CategoryRepo;
import com.harshit.blogapi.repositories.PostRepo;
import com.harshit.blogapi.repositories.UserRepo;
import com.harshit.blogapi.services.PostService;
import com.harshit.blogapi.entities.*;


@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " id ", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		Post post =modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		
		Post newPost = this.postrepo.save(post);
		
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		if(postDto.getTitle()!=null) {
			post.setTitle(postDto.getTitle());
		}
		post.setTitle(postDto.getTitle());
		if(postDto.getContent()!=null) {
			post.setContent(postDto.getContent());
		}
		if(postDto.getImageName()!=null) {
			post.setImageName(postDto.getImageName());	
		}
		Post updatedPost = this.postrepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		this.postrepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).descending()); 
		
		
		Page<Post> pagePost = this.postrepo.findAll(pageable);
		List<Post> allPost = pagePost.getContent();
		List<PostDto> postsDto = allPost.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postsDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastpage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", " id ", postId));

		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = this.postrepo.findByCategory(cat);
		List<PostDto> postsDto = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " id ", userId));
		List<Post> posts = this.postrepo.findByUser(user);
		List<PostDto> postsDto = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postrepo.findByTitleContaining("%"+keyword+"%");
		List<PostDto> postsDto = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postsDto;
	}

}
