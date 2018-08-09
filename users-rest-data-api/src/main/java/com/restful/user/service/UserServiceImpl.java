package com.restful.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.restful.exception.UserNotFoundException;
import com.restful.user.data.AccountResponseData;
import com.restful.user.data.User;
import com.restful.user.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountProxyService accountProxyService;

	@Override
	public List<User> findAllUser(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, new Sort(Direction.ASC, "firstName"));
		List<User> users = new ArrayList<>();
		// userRepository.findAll(pageable).forEach(users::add);
		userRepository.findAll(pageable).forEach(user -> {
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(user.getId());
			user.setAccount(accountResponseData);
			users.add(user);
		});
		return users;
	}

	@Override
	public List<User> findAllUserByFirstNameAndLastName(String firstName, String lastName) {
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<User> findAllUserByEmail(String email) {
		return userRepository.findByEmailLike("%" + email + "%");
	}

	@Override
	public User findUser(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if (!optional.isPresent()) {
			throw new UserNotFoundException("UserId " + id + " doesn't exist !!");
		} else {
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(id);
			optional.get().setAccount(accountResponseData);
			return optional.get();
		}
	}

	@Override
	public User saveUser(User user) {
		if (user.getPosts() != null) {
			// If we are passing post with user then we have to do reverse assoication by
			// setting user on post object
			user.getPosts().forEach(post -> post.setUser(user));
		}
		return userRepository.save(user);
	}

	@Override
	public HttpStatus deleteUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
			return HttpStatus.NO_CONTENT;
		} else {
			return HttpStatus.ACCEPTED;
		}
	}

	@Override
	public User updateUser(Long id, User user) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			if (user.getPosts() != null) {
				user.getPosts().forEach(post -> post.setUser(user));
			}
			return userRepository.save(user);
		} else {
			throw new UserNotFoundException("UserId " + id + " doesn't exist !!");
		}
	}
}
