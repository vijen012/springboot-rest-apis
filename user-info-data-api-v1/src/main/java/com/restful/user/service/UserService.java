package com.restful.user.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.restful.user.data.User;

public interface UserService {

	public List<User> findAllUser(int pageNumber, int pageSize);

	public User findUser(Long id);

	public User saveUser(User user);

	public HttpStatus deleteUser(Long id);

	public User updateUser(Long id, User user);

	public List<User> findAllUserByFirstNameAndLastName(String firstName, String lastName);

	public List<User> findAllUserByEmail(String email);
}
