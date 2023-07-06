package com.harshit.blogapi.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshit.blogapi.payloads.CommentDto;
import com.harshit.blogapi.repositories.CommentRepo;
import com.harshit.blogapi.repositories.PostRepo;
import com.harshit.blogapi.services.CommentService;
import com.harshit.blogapi.entities.Post;
import com.harshit.blogapi.exceptions.ResourceNotFoundException;
import com.harshit.blogapi.entities.Comment;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private CommentRepo commentrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = this.modelmapper.map(commentDto, Comment.class);
		comment.setPost(post);
		
		Comment savedComment = this.commentrepo.save(comment);
		return this.modelmapper.map(savedComment, CommentDto.class); 
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentrepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "commentId", commentId));
		this.commentrepo.delete(comment);
	}


}
