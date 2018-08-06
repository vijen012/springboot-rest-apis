package com.restful.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.restful.exception.PostNotFoundException;
import com.restful.exception.UserNotFoundException;
import com.restful.post.data.Post;
import com.restful.post.repos.PostRepository;
import com.restful.user.data.User;
import com.restful.user.repos.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {

	@Mock
	private PostRepository postRepository;

	@Mock
	private UserRepository userRepository;

	@Autowired
	@InjectMocks
	private PostServiceImpl postService;

	@Before
	public void setUp() throws Exception {
		// MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findAllPostByUserId_ShouldReturnAllThePostsForTheUserWhenUserIsExistAndPostIsNotNullOrEmpty() {
		User user = new User(101L, "Martin", "Hussy", "martin.hussy@gmail.com", LocalDate.of(2016, 6, 12));
		Post post = new Post();
		post.setId(201L);
		post.setComment("first comment");
		List<Post> postList = new ArrayList<>();
		postList.add(post);
		user.setPosts(postList);
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		assertThat(postService.findAllPostByUserId(anyLong())).hasSize(1);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void findAllPostByUserId_ShouldReturnEmptyListForTheUserWhenUserIsExistAndPostIsNullOrEmpty() {
		User user = new User(101L, "Martin", "Hussy", "martin.hussy@gmail.com", LocalDate.of(2016, 6, 12));
		List<Post> postList = new ArrayList<>();
		user.setPosts(postList);
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		assertThat(postService.findAllPostByUserId(anyLong())).hasSize(0);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test(expected = UserNotFoundException.class)
	public void findAllPostByUserId_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		postService.findAllPostByUserId(anyLong());
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void findPost_ShouldReturnThePostWhenPostIsExist() {
		Post post = new Post();
		post.setId(201L);
		post.setComment("first comment");
		Optional<Post> optionalPost = Optional.of(post);
		when(postRepository.findById(anyLong())).thenReturn(optionalPost);
		assertThat(postService.findPost(anyLong())).isEqualTo(optionalPost.get());
		verify(postRepository, times(1)).findById(anyLong());
	}

	@Test(expected = PostNotFoundException.class)
	public void findPost_ShouldThrowPostNotFoundExceptionWhenPostIdDoesNotExist() {
		Optional<Post> optionalPost = Optional.empty();
		when(postRepository.findById(anyLong())).thenReturn(optionalPost);
		postService.findPost(anyLong());
		verify(postRepository, times(1)).findById(anyLong());
	}

	@Test
	public void savePost_ShouldReturnSavedPostWhenUserIdIsExist() {
		User user = new User(101L, "Martin", "Hussy", "martin.hussy@gmail.com", LocalDate.of(2016, 6, 12));
		Post post = new Post();
		post.setId(201L);
		post.setComment("first comment");
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(postRepository.save(any(Post.class))).thenReturn(post);
		assertThat(postService.save(anyLong(), post).getUser()).isEqualTo(user);
		verify(userRepository, times(1)).findById(anyLong());
		verify(postRepository, times(1)).save(any(Post.class));
	}

	@Test(expected = UserNotFoundException.class)
	public void savePost_ShouldThrowUserNotFoundWhenUserIdDoesNotExist() {
		Post post = new Post();
		post.setId(201L);
		post.setComment("first comment");
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		postService.save(anyLong(), post);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void updatePost_ShouldReturnUpdatedPostWhenUserIdIsExist() {
		User user = new User(101L, "Martin", "Hussy", "martin.hussy@gmail.com", LocalDate.of(2016, 6, 12));
		Post post = new Post();
		post.setId(201L);
		post.setComment("first comment");
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(postRepository.save(any(Post.class))).thenReturn(post);
		assertThat(postService.updatePost(anyLong(), post).getUser()).isEqualTo(user);
		verify(userRepository, times(1)).findById(anyLong());
		verify(postRepository, times(1)).save(any(Post.class));
	}

	@Test(expected = UserNotFoundException.class)
	public void updatePost_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
		Post post = new Post();
		post.setId(201L);
		post.setComment("first comment");
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		postService.updatePost(anyLong(), post);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void updateAllPost_ShouldReturnAllTheUpdatedPostWhenUserIsExist() {
		User user = new User(101L, "Martin", "Hussy", "martin.hussy@gmail.com", LocalDate.of(2016, 6, 12));
		Post post1 = new Post();
		post1.setId(201L);
		post1.setComment("first comment");
		Post post2 = new Post();
		post2.setId(201L);
		post2.setComment("second comment");
		List<Post> postList = new ArrayList<>();
		postList.add(post1);
		postList.add(post2);

		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		when(postRepository.saveAll(anyIterable())).thenReturn(postList);

		postService.updateAllPost(anyLong(), postList).forEach(post -> assertThat(post.getUser()).isEqualTo(user));
		verify(userRepository, times(1)).findById(anyLong());
		verify(postRepository, times(1)).saveAll(anyIterable());
	}

	@Test(expected = UserNotFoundException.class)
	public void updateAllPost_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
		Post post1 = new Post();
		post1.setId(201L);
		post1.setComment("first comment");
		Post post2 = new Post();
		post2.setId(201L);
		post2.setComment("second comment");
		List<Post> postList = new ArrayList<>();
		postList.add(post1);
		postList.add(post2);
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		postService.updateAllPost(anyLong(), postList);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test
	public void deletePost_ShouldReturnHttpStatusNoContentWhenPostIsExist() {
		Post post = new Post();
		post.setId(201L);
		post.setComment("first comment");
		Optional<Post> optionalPost = Optional.of(post);
		when(postRepository.findById(anyLong())).thenReturn(optionalPost);
		doNothing().when(postRepository).deleteById(anyLong());
		assertThat(postService.deletePost(anyLong())).isEqualTo(HttpStatus.NO_CONTENT);
		verify(postRepository, times(1)).findById(anyLong());
		verify(postRepository, times(1)).deleteById(anyLong());
	}

	@Test
	public void deletePost_ShouldReturnHttpStatusAcceptedWhenPostDoesNotExist() {
		Optional<Post> optionalPost = Optional.empty();
		when(postRepository.findById(anyLong())).thenReturn(optionalPost);
		assertThat(postService.deletePost(anyLong())).isEqualTo(HttpStatus.ACCEPTED);
		verify(postRepository, times(1)).findById(anyLong());
	}

	@Test
	public void deleteAllPosts_ShouldReturnHttpStatusNoContentWhenUserIsExistAndPostListIsNotEmpty() {
		User user = new User(101L, "Martin", "Hussy", "martin.hussy@gmail.com", LocalDate.of(2016, 6, 12));
		Post post1 = new Post();
		post1.setId(201L);
		post1.setComment("first comment");
		Post post2 = new Post();
		post2.setId(201L);
		post2.setComment("second comment");
		List<Post> postList = new ArrayList<>();
		postList.add(post1);
		postList.add(post2);
		user.setPosts(postList);

		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		doNothing().when(postRepository).deleteAll(anyIterable());

		assertThat(postService.deleteAllPost(anyLong())).isEqualTo(HttpStatus.NO_CONTENT);
		verify(userRepository, times(1)).findById(anyLong());
		verify(postRepository, times(1)).deleteAll(anyIterable());
	}

	@Test
	public void deleteAllPosts_ShouldReturnHttpStatusAcceptedWhenUserIsExistButPostListIsEmpty() {
		User user = new User(101L, "Martin", "Hussy", "martin.hussy@gmail.com", LocalDate.of(2016, 6, 12));
		List<Post> postList = new ArrayList<>();
		user.setPosts(postList);
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		assertThat(postService.deleteAllPost(anyLong())).isEqualTo(HttpStatus.ACCEPTED);
		verify(userRepository, times(1)).findById(anyLong());
	}

	@Test(expected = UserNotFoundException.class)
	public void deleteAllPosts_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
		Optional<User> optionalUser = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(optionalUser);
		postService.deleteAllPost(anyLong());
		verify(userRepository, times(1)).findById(anyLong());
	}

}
