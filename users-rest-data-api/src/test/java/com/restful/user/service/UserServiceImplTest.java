package com.restful.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;

import com.restful.exception.UserNotFoundException;
import com.restful.post.data.Post;
import com.restful.user.data.AccountResponseData;
import com.restful.user.data.AccountType;
import com.restful.user.data.User;
import com.restful.user.repos.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private AccountProxyService accountProxyService;

	@Autowired
	@InjectMocks
	private UserServiceImpl userService;

	private List<User> userList = Stream
			.of(new User(101L, "Martin", "Hussy", "martin.hussy@gmail.com", LocalDate.of(2016, 8, 12)),
					new User(102L, "Jack", "Daniel", "jack.daniel@gmail.com", LocalDate.of(2016, 2, 12)),
					new User(103L, "Cess", "Martin", "cess.martin@gmail.com", LocalDate.of(2016, 3, 12)))
			.collect(Collectors.toList());

	private User user = new User(101L, "Martin", "Hussy", "martin.hussy@gmail.com", LocalDate.of(2016, 6, 12));
	private AccountResponseData account = new AccountResponseData(1000L, AccountType.SAVING, "1223BBC", 2000d);

	@Before
	public void setUp() {
		// Not needed if we have used @RunWith(MockitoJUnitRunner.class) - Enable
		// Mockito annotations
		// MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllUser_ShouldReturnAllTheUsersWhenRecordsAreExist() {
		Pageable pageable = PageRequest.of(0, 3, new Sort(Direction.ASC, "firstName"));
		Page<User> pageUsers = new PageImpl<>(userList, pageable, userList.size());
		when(userRepository.findAll(pageable)).thenReturn(pageUsers);
		when(accountProxyService.getAccountDetail(anyLong())).thenReturn(account);
		assertThat(userService.findAllUser(0, 3)).hasSize(3);
		verify(userRepository, times(1)).findAll(pageable);
		verify(accountProxyService, times(3)).getAccountDetail(anyLong());
	}

	@Test
	public void findAllUser_ShouldReturnEmptyUserListWhenThereAreNotRecordInDatabase() {
		List<User> userList = new ArrayList<>();
		Pageable pageable = PageRequest.of(0, 3, new Sort(Direction.ASC, "firstName"));
		Page<User> pageUsers = new PageImpl<>(userList, pageable, userList.size());
		when(userRepository.findAll(pageable)).thenReturn(pageUsers);
		assertThat(userService.findAllUser(0, 3)).hasSize(0);
		verify(userRepository, times(1)).findAll(pageable);
	}

	@Test
	public void findUser_ShouldReturnTheUserWhenUserIdIsValidAndAccountObjectIsNotNull() {
		Optional<User> userOptional = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(userOptional);
		when(accountProxyService.getAccountDetail(anyLong())).thenReturn(account);
		assertThat(userService.findUser(101L)).isEqualTo(userOptional.get());
		assertThat(userService.findUser(101L).getAccount()).isEqualTo(account);
		verify(userRepository, times(2)).findById(anyLong());
		verify(accountProxyService, times(2)).getAccountDetail(anyLong());
	}

	@Test
	public void findUser_ShouldReturnTheUserWhenUserIdIsValidAndAccountObjectIsNull() {
		Optional<User> userOptional = Optional.of(user);
		when(userRepository.findById(anyLong())).thenReturn(userOptional);
		when(accountProxyService.getAccountDetail(anyLong())).thenReturn(null);
		assertThat(userService.findUser(101L)).isEqualTo(userOptional.get());
		assertThat(userService.findUser(101L).getAccount()).isEqualTo(null);
		verify(userRepository, times(2)).findById(anyLong());
		verify(accountProxyService, times(2)).getAccountDetail(anyLong());
	}

	@Test(expected = UserNotFoundException.class)
	public void findUser_ShouldThrowTheUserNotFoundExceptionWhenUserDoesNotExist() {
		Optional<User> value = Optional.empty();
		when(userRepository.findById(anyLong())).thenReturn(value);
		userService.findUser(101L);
	}

	@Test
	public void saveUser_ShouldReturnSameUserInstanceAfterSavedWhenNoPostsAssociateWithUserInstance() {
		when(userRepository.save(any(User.class))).thenReturn(user);
		assertThat(userService.saveUser(user)).isEqualTo(user);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	public void saveUser_ShouldReturnSameUserInstanceAfterSavedWhenPostsAssociateWithUserInstance() {
		List<Post> postList = new ArrayList<>();
		Post post = new Post();
		post.setId(201L);
		post.setComment("first comment");
		postList.add(post);
		user.setPosts(postList);
		when(userRepository.save(any(User.class))).thenReturn(user);
		assertThat(userService.saveUser(user)).isEqualTo(user);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	public void deleteUser_ShouldReturnHttpStatusNoContentWhenUserIdIsValid() {
		Optional<User> userOptional = Optional.of(user);
		when(userRepository.findById(101L)).thenReturn(userOptional);
		doNothing().when(userRepository).deleteById(anyLong());
		assertThat(userService.deleteUser(101L)).isEqualTo(HttpStatus.NO_CONTENT);
		verify(userRepository, times(1)).findById(anyLong());
		verify(userRepository, times(1)).deleteById(anyLong());
	}

	@Test
	public void deleteUser_ShouldReturnHttpStatusAcceptedWhenUserIdIsInvalid() {
		Optional<User> userOptional = Optional.empty();
		when(userRepository.findById(101L)).thenReturn(userOptional);
		// doThrow(EmptyResultDataAccessException.class).when(userRepository).deleteById(anyLong());
		assertThat(userService.deleteUser(101L)).isEqualTo(HttpStatus.ACCEPTED);
		verify(userRepository, times(0)).deleteById(anyLong());
	}

	@Test
	public void updateUser_ShouldReturnSameUserInstanceAfterSavedWhenNoPostsAssociateWithUserInstance() {
		Optional<User> userOptional = Optional.of(user);
		when(userRepository.findById(101L)).thenReturn(userOptional);
		when(userRepository.save(any(User.class))).thenReturn(user);
		assertThat(userService.updateUser(101L, user)).isEqualTo(user);
		verify(userRepository, times(1)).findById(anyLong());
		verify(userRepository, times(1)).save(user);
	}

	@Test
	public void updateUser_ShouldReturnSameUserInstanceAfterSavedWhenPostsAssociateWithUserInstance() {
		Optional<User> userOptional = Optional.of(user);
		List<Post> postList = new ArrayList<>();
		Post post = new Post();
		post.setId(201L);
		post.setComment("first comment");
		postList.add(post);
		user.setPosts(postList);

		when(userRepository.findById(101L)).thenReturn(userOptional);
		when(userRepository.save(any(User.class))).thenReturn(user);
		assertThat(userService.updateUser(101L, user)).isEqualTo(user);
		verify(userRepository, times(1)).findById(anyLong());
		verify(userRepository, times(1)).save(user);
	}

	@Test(expected = UserNotFoundException.class)
	public void updateUser_ShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
		Optional<User> userOptional = Optional.empty();
		when(userRepository.findById(101L)).thenReturn(userOptional);
		userService.updateUser(101L, user);
		verify(userRepository, times(1)).findById(anyLong());
	}
}
