package com.restful.user.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
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
	private Logger logger;

	@Override
	public List<UserResponseData> findAllUser(int pageNumber, int pageSize) {
		logger.trace("Enter ---> ");
		UserDataMapper userDataMapper = new UserDataMapper();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, new Sort(Direction.ASC, "firstName"));
//		Map<User, AccountResponseData> userResponseDataMap = new HashMap<>();
		Map<User, AccountResponseData> userResponseDataMap = new LinkedHashMap<>();
		logger.info("fetching user records --->");
		userRepository.findAll(pageable).forEach(user -> {
			logger.info("fetching account details from account-info-data api for userId: " + user.getId());
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(user.getId());
			logger.info("fetched account details for userId ---> " + user.getId());
			userResponseDataMap.put(user, accountResponseData);
		});
		List<UserResponseData> userResponseDataList = userDataMapper.getUserResponseDataList(userResponseDataMap);
		logger.trace("<--- Exit");
		return userResponseDataList;
	}

	@Override
	public List<UserResponseData> findAllUserByFirstNameAndLastName(String firstName, String lastName) {
		logger.trace("Enter ---> ");
		UserDataMapper userDataMapper = new UserDataMapper();
//		Map<User, AccountResponseData> userResponseDataMap = new HashMap<>();
		Map<User, AccountResponseData> userResponseDataMap = new LinkedHashMap<>();
		logger.info("fetching user records --->");
		userRepository.findByFirstNameAndLastName(firstName, lastName).forEach(user -> {
			logger.info("fetching account details from account-info-data-api for userId: " + user.getId());
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(user.getId());
			logger.info("fetched account details for userId ---> " + user.getId());
			userResponseDataMap.put(user, accountResponseData);
		});
		List<UserResponseData> userResponseDataList = userDataMapper.getUserResponseDataList(userResponseDataMap);
		logger.trace("<--- Exit");
		return userResponseDataList;
	}

	@Override
	public List<UserResponseData> findAllUserByEmail(String email) {
		logger.trace("Enter ---> ");
		UserDataMapper userDataMapper = new UserDataMapper();
//		Map<User, AccountResponseData> userResponseDataMap = new HashMap<>();
		Map<User, AccountResponseData> userResponseDataMap = new LinkedHashMap<>();
		logger.info("fetching user records --->");
		userRepository.findByEmailLike("%" + email + "%").forEach(user -> {
			logger.info("fetching account details from account-info-data-api for userId: " + user.getId());
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(user.getId());
			logger.info("fetched account details for userId ---> " + user.getId());
			userResponseDataMap.put(user, accountResponseData);
		});
		List<UserResponseData> userResponseDataList = userDataMapper.getUserResponseDataList(userResponseDataMap);
		logger.trace("<--- Exit");
		return userResponseDataList;
	}

	@Override
	public UserResponseData findUser(Long id) {
		logger.trace(id + " Enter ---> ");
		UserDataMapper userDataMapper = new UserDataMapper();
		logger.info("fecthing records for userId " + id);
		Optional<User> optional = userRepository.findById(id);
		if (!optional.isPresent()) {
			UserNotFoundException userNotFoundException = new UserNotFoundException(
					"UserId " + id + " doesn't exist !!");
			logger.error(userNotFoundException + " <--- Exit");
			throw userNotFoundException;
		} else {
			logger.info("fetching account details from account-info-data api for userId: " + id);
			AccountResponseData accountResponseData = accountProxyService.getAccountDetail(id);
			UserResponseData userResponseData = userDataMapper.getUserResponseData(optional.get(), accountResponseData);
			logger.trace(id + " <--- Exit");
			return userResponseData;
		}
	}

	@Override
	public UserResponseData saveUser(UserRequestData userRequestData) {
		logger.trace("Enter --->");
		UserDataMapper userDataMapper = new UserDataMapper();
		User user = userDataMapper.getUser(userRequestData);
		if (user.getAddressList() != null) {
			// If we are passing address with user then we have to do reverse assoication by
			// setting user on address object
			user.getAddressList().forEach(address -> address.setUser(user));
		}
		logger.info("persisting user entity in database --->");
		User savedUser = userRepository.save(user);
		UserResponseData userResponseData = userDataMapper.getUserResponseData(savedUser, null);
		logger.trace("<--- Exit");
		return userResponseData;
	}

	@Override
	public HttpStatus deleteUser(Long id) {
		logger.trace(id + " Enter --->");
		logger.info("fecthing records for userId " + id);
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
			logger.trace(id + " <--- Exit");
			return HttpStatus.NO_CONTENT;
		} else {
			logger.trace(id + " <--- Exit");
			return HttpStatus.ACCEPTED;
		}
	}

	@Override
	public UserResponseData updateUser(Long id, UserRequestData userRequestData) {
		logger.trace(id + " Enter --->");
		UserDataMapper userDataMapper = new UserDataMapper();
		logger.info("fecthing records for userId " + id);
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userDataMapper.getUser(userRequestData);
			if (user.getAddressList() != null) {
				user.getAddressList().forEach(address -> address.setUser(user));
			}
			logger.info("persisting user entity in database --->");
			User savedUser = userRepository.save(user);
			UserResponseData userResponseData = userDataMapper.getUserResponseData(savedUser,
					userRequestData.getAccountResponseData());
			logger.trace(id + " <--- Exit");
			return userResponseData;
		} else {
			UserNotFoundException userNotFoundException = new UserNotFoundException(
					"UserId " + id + " doesn't exist !!");
			logger.error(userNotFoundException + " <--- Exit");
			throw userNotFoundException;
		}
	}
}
