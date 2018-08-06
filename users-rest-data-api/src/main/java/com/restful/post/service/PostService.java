package com.restful.post.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

import com.restful.post.data.Post;

public interface PostService {
	public List<Post> findAllPostByUserId(Long userId);

	public Post findPost(Long postId);

	public Post save(Long userId, Post post);

	public Post updatePost(Long userId, @Valid Post post);

	public List<Post> updateAllPost(Long userId, @Valid List<Post> posts);

	public HttpStatus deletePost(Long postId);

	public HttpStatus deleteAllPost(Long userId);
}
