package com.restful.post.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restful.post.data.Post;
import com.restful.post.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping(path = "/users/{userId}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> getAllPosts(@PathVariable Long userId) {
		return new ResponseEntity<List<Post>>(postService.findAllPostByUserId(userId), HttpStatus.OK);
	}

	@GetMapping(path = "/users/{userId}/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Post> getPost(@PathVariable("postId") Long postId) {
		return new ResponseEntity<>(postService.findPost(postId), HttpStatus.OK);
	}

	@PostMapping(path = "/users/{userId}/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Post> createPost(@PathVariable Long userId, @Valid @RequestBody(required = true) Post post) {
		Post savedPost = postService.save(userId, post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<Post>(savedPost, headers, HttpStatus.CREATED);
	}

	@PutMapping(path = "/users/{userId}/posts/{postId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Post> updatePost(@PathVariable Long userId, @PathVariable Long postId,
			@Valid @RequestBody(required = true) Post post) {
		return new ResponseEntity<>(postService.updatePost(userId, post), HttpStatus.OK);
	}

	@PutMapping(path = "/users/{userId}/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Post>> updateAllPost(@PathVariable Long userId,
			@Valid @RequestBody(required = true) List<Post> posts) {
		return new ResponseEntity<>(postService.updateAllPost(userId, posts), HttpStatus.OK);
	}

	@DeleteMapping(path = "/users/{userId}/posts/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Long userId, @PathVariable Long postId) {
		return new ResponseEntity<Void>(postService.deletePost(postId));
	}

	@DeleteMapping(path = "/users/{userId}/posts")
	public ResponseEntity<Void> deleteAllPost(@PathVariable Long userId) {
		return new ResponseEntity<Void>(postService.deleteAllPost(userId));
	}

}
