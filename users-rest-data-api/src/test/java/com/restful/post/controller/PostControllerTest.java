package com.restful.post.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.restful.exception.PostNotFoundException;
import com.restful.exception.UserNotFoundException;
import com.restful.post.data.Post;
import com.restful.post.service.PostService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PostController.class)
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private RestTemplate restTemplate;

	@MockBean
	private PostService postService;

	private List<Post> postList = Stream.of(new Post(201L, "first comment"), new Post(201L, "first comment"))
			.collect(Collectors.toList());
	private Post post = new Post(201L, "first Comment");

	@Before
	public void setUp() {
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	@Test
	public void getAllPost_ShouldReturnAllThePostWhenPostDoesExistForGivenUserId()
			throws JsonProcessingException, Exception {
		when(postService.findAllPostByUserId(anyLong())).thenReturn(postList);
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/posts")
				.accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(postList)))
				.andReturn();
		// @formatter:on
		verify(postService).findAllPostByUserId(anyLong());
	}

	@Test
	public void getAllPost_ShouldReturnAllThePostWhenPostDoesNotExistForGivenUserId()
			throws JsonProcessingException, Exception {
		List<Post> postList = new ArrayList<>();
		when(postService.findAllPostByUserId(anyLong())).thenReturn(postList);
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/posts")
				.accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(postList)))
				.andReturn();
		// @formatter:on
		verify(postService).findAllPostByUserId(anyLong());
	}

	@Test
	public void getAllPost_ShouldThrowExceptionWhenPathVariableUserIdIsString()
			throws JsonProcessingException, Exception {
		when(postService.findAllPostByUserId(anyLong())).thenReturn(postList);
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101str/posts")
				.accept(MediaType.APPLICATION_JSON_VALUE);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isBadRequest());
		// @formatter:on
	}

	@Test
	public void getPost_ShoudlReturnThePostWhenGivenPostIdIsValid() throws JsonProcessingException, Exception {
		when(postService.findPost(anyLong())).thenReturn(post);
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/posts/201")
				.accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(post))).andReturn();
		// @formatter:on
		verify(postService).findPost(anyLong());

	}

	@Test
	public void getPost_ShoudlThrowPostNotFoundExceptionWhenGivenPostIdDoesNotExist()
			throws JsonProcessingException, Exception {
		doThrow(PostNotFoundException.class).when(postService).findPost(anyLong());
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/posts/201")
				.accept(MediaType.APPLICATION_JSON_VALUE);
		mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
		// @formatter:on
		verify(postService).findPost(anyLong());
	}

	@Test
	public void getPost_ShoudlThrowBadRequestWhenGivenPostIdIsString() throws Exception {
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/posts/201kl")
				.accept(MediaType.APPLICATION_JSON_VALUE);
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
		// @formatter:on
	}

	@Test
	public void createPost_ShouldReturnSavedPostWhenRequestPayloadAndUserIdIsValid()
			throws JsonProcessingException, Exception {
		when(postService.save(anyLong(), any(Post.class))).thenReturn(post);
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/101/posts")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(post))
				.accept(MediaType.APPLICATION_JSON_VALUE);
		MvcResult mvcResult = mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(post)))
				.andReturn();
		// @formatter:on
		verify(postService).save(anyLong(), any(Post.class));
	}

	@Test
	public void createPost_ShouldUserNotFoundExeceptionWhenRequestPayloadIsValidButUserIdDoesNotExist()
			throws JsonProcessingException, Exception {
		doThrow(UserNotFoundException.class).when(postService).save(anyLong(), any(Post.class));
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/101/posts")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(post))
				.accept(MediaType.APPLICATION_JSON_VALUE);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isNotFound());
		// @formatter:on
		verify(postService).save(anyLong(), any(Post.class));
	}

	@Test
	public void createPost_ShouldThrowBadRequestWhenRequestPayloadAndUserIdIsString()
			throws JsonProcessingException, Exception {
		when(postService.save(anyLong(), any(Post.class))).thenReturn(post);
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/101kl/posts")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(post))
				.accept(MediaType.APPLICATION_JSON_VALUE);
		mockMvc.perform(requestBuilder)
				.andExpect(status().isBadRequest());
		// @formatter:on
	}
}
