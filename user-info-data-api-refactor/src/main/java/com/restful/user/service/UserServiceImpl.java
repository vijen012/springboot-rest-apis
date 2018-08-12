package com.restful.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.restful.user.data.UserDataMapper;
import com.restful.user.data.UserRequestData;
import com.restful.user.data.UserResponseData;
import com.restful.user.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountProxyService accountProxyService;

	@Autowired
	private UserDataMapper userDataMapper;

	@Override
	public List<UserResponseData> findAllUser(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, new Sort(Direction.ASC, "firstName"));
		Map<User, AccountResponseData> userResponseDataMap = new HashMap<>();
		userRepository.findAll(pageable).forEach(user -> {
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(user.getId());
			userResponseDataMap.put(user, accountResponseData);
		});
		return userDataMapper.getUserResponseDataList(userResponseDataMap);
	}

	@Override
	public List<UserResponseData> findAllUserByFirstNameAndLastName(String firstName, String lastName) {
		Map<User, AccountResponseData> userResponseDataMap = new HashMap<>();
		userRepository.findByFirstNameAndLastName(firstName, lastName).forEach(user -> {
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(user.getId());
			userResponseDataMap.put(user, accountResponseData);
		});
		return userDataMapper.getUserResponseDataList(userResponseDataMap);
	}

	@Override
	public List<UserResponseData> findAllUserByEmail(String email) {
		Map<User, AccountResponseData> userResponseDataMap = new HashMap<>();
		userRepository.findByEmailLike("%" + email + "%").forEach(user -> {
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(user.getId());
			userResponseDataMap.put(user, accountResponseData);
		});
		return userDataMapper.getUserResponseDataList(userResponseDataMap);
	}

	@Override
	public UserResponseData findUser(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if (!optional.isPresent()) {
			throw new UserNotFoundException("UserId " + id + " doesn't exist !!");
		} else {
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(id);
			return userDataMapper.getUserResponseData(optional.get(), accountResponseData);
		}
	}

	@Override
	public UserResponseData saveUser(UserRequestData userRequestData) {
		User user = userDataMapper.getUser(userRequestData);
		if (user.getAddressList() != null) {
			// If we are passing address with user then we have to do reverse assoication by
			// setting user on address object
			user.getAddressList().forEach(address -> address.setUser(user));
		}
		User savedUser = userRepository.save(user);
		UserResponseData userResponseData = userDataMapper.getUserResponseData(savedUser, null);
		return userResponseData;
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
	public UserResponseData updateUser(Long id, UserRequestData userRequestData) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userDataMapper.getUser(userRequestData);
			if (user.getAddressList() != null) {
				user.getAddressList().forEach(address -> address.setUser(user));
			}
			User savedUser = userRepository.save(user);
			UserResponseData userResponseData = userDataMapper.getUserResponseData(savedUser, user.getAccount());
			return userResponseData;
		} else {
			throw new UserNotFoundException("UserId " + id + " doesn't exist !!");
		}
	}
}
