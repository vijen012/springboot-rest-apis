package com.restful.user.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.restful.user.data.UserRequestData;
import com.restful.user.data.UserResponseData;

public interface UserService {

	public List<UserResponseData> findAllUser(int pageNumber, int pageSize);

	public UserResponseData findUser(Long id);

	public UserResponseData saveUser(UserRequestData userRequestData);

	public HttpStatus deleteUser(Long id);

	public UserResponseData updateUser(Long id, UserRequestData userRequestData);

	public List<UserResponseData> findAllUserByFirstNameAndLastName(String firstName, String lastName);

	public List<UserResponseData> findAllUserByEmail(String email);
}
