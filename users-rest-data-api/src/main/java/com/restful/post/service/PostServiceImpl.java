package com.restful.post.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.restful.exception.PostNotFoundException;
import com.restful.exception.UserNotFoundException;
import com.restful.post.data.Post;
import com.restful.post.repos.PostRepository;
import com.restful.user.data.User;
import com.restful.user.repos.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepositroy;

	@Override
	public List<Post> findAllPostByUserId(Long userId) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		return userOptional.get().getPosts();
	}

	@Override
	public Post findPost(Long postId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		if (!postOptional.isPresent()) {
			throw new PostNotFoundException("PostId " + postId + " doesn't exist !!");
		}
		return postOptional.get();
	}

	@Override
	public Post save(Long userId, Post post) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		User user = userOptional.get();
		post.setUser(user);
		return postRepository.save(post);
	}

	@Override
	public Post updatePost(Long userId, @Valid Post post) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		User user = userOptional.get();
		post.setUser(user);
		return postRepository.save(post);
	}

	@Override
	public List<Post> updateAllPost(Long userId, @Valid List<Post> posts) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		}
		User user = userOptional.get();
		posts.forEach(post -> post.setUser(user));
		return (List<Post>) postRepository.saveAll(posts);
	}

	@Override
	public HttpStatus deletePost(Long postId) {
		Optional<Post> postOptional = postRepository.findById(postId);
		if (!postOptional.isPresent()) {
			return HttpStatus.ACCEPTED;
		}
		// Post post = postOptional.get();
		// post.setUser(null);
		postRepository.deleteById(postId);
		return HttpStatus.NO_CONTENT;
	}

	@Override
	public HttpStatus deleteAllPost(Long userId) {
		Optional<User> userOptional = userRepositroy.findById(userId);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("UserId " + userId + " doesn't exist !!");
		} else if (userOptional.get().getPosts() == null || userOptional.get().getPosts().isEmpty()) {
			return HttpStatus.ACCEPTED;
		}
		User user = userOptional.get();
		user.getPosts().forEach(post -> post.setUser(null));
		postRepository.deleteAll(user.getPosts());
		return HttpStatus.NO_CONTENT;
	}

}
