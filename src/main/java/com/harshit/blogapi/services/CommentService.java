package com.harshit.blogapi.services;

import com.harshit.blogapi.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId);
	void deleteComment(Integer commentId);
}