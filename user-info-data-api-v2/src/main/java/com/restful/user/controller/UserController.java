package com.restful.user.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restful.user.data.UserRequestData;
import com.restful.user.data.UserResponseData;
import com.restful.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Logger logger;
	/*
	 * @Value("${accountApiUrl}") private String accountApiUrl;
	 */

	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserResponseData>> getAllUsers(
			@RequestHeader(value = "x-request-header", required = true) @Valid final String requestHeader,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) final int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) final int pageSize,
			final HttpServletRequest request) {
		logger.trace("Enter ---> " + request.getRequestURI());
		logger.info("Fetching " + pageSize + " records for page " + pageNumber);
		ResponseEntity<List<UserResponseData>> responseEntity = new ResponseEntity<List<UserResponseData>>(
				userService.findAllUser(pageNumber, pageSize), HttpStatus.OK);
		logger.trace("<---Exit");
		return responseEntity;
	}

	@GetMapping(path = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseData> getUser(@PathVariable final Long userId, final HttpServletRequest request) {
		logger.trace("Enter ---> " + request.getRequestURL());
		ResponseEntity<UserResponseData> responseEntity = new ResponseEntity<UserResponseData>(
				userService.findUser(userId), HttpStatus.OK);
		logger.trace(userId + " <---Exit");
		return responseEntity;
	}

	@PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseData> createUser(
			@Valid @RequestBody(required = true) final UserRequestData userRequestData,
			final HttpServletRequest request) {
		logger.trace("Enter ---> " + request.getRequestURL());
		UserResponseData savedUser = userService.saveUser(userRequestData);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		ResponseEntity<UserResponseData> responseEntity = new ResponseEntity<UserResponseData>(savedUser, headers,
				HttpStatus.CREATED);
		logger.trace("<---Exit");
		return responseEntity;
	}

	@DeleteMapping(path = "/users/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable final Long userId, final HttpServletRequest request) {
		logger.trace("Enter ---> " + request.getRequestURL());
		ResponseEntity<Void> responseEntity = new ResponseEntity<Void>(userService.deleteUser(userId));
		logger.trace(userId + " <---Exit");
		return responseEntity;
	}

	@PutMapping(path = "/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponseData> updateUser(@PathVariable final Long userId,
			@Valid @RequestBody(required = true) final UserRequestData userRequestData,
			final HttpServletRequest request) {
		logger.trace("Enter ---> " + request.getRequestURL());
		ResponseEntity<UserResponseData> responseEntity = new ResponseEntity<UserResponseData>(
				userService.updateUser(userId, userRequestData), HttpStatus.OK);
		logger.trace(userId + " <---Exit");
		return responseEntity;
	}

//	/*------------------------Custome Finder Methods----------------------------*/
//
//	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<User>> getAllUsersByFirstNameAndLastName(
//			@RequestParam(value = "firstName", required = true) String firstName,
//			@RequestParam(value = "lastName", required = true) String lastName) {
//		return new ResponseEntity<List<User>>(userService.findAllUserByFirstNameAndLastName(firstName, lastName),
//				HttpStatus.OK);
//	}
//
//	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<User>> getAllUsersByEmail(
//			@RequestParam(value = "email", required = false) String email) {
//		return new ResponseEntity<List<User>>(userService.findAllUserByEmail(email), HttpStatus.OK);
//	}
//
//	/*------------------------Dynamic filtering----------------------------*/
//
//	@GetMapping(path = "/users-filter/{id}", produces = { "application/json", "application/xml" })
//	public MappingJacksonValue getFilterUser(@PathVariable("id") int id) {
//		User user = userService.findOne(id);
//		if (user == null) {
//			throw new UserNotFoundException("id-" + id);
//		}
//
//		// it will ignore all the fields apart from id and name
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
//
//		// have to add @JsonFilter("UserFileter") annotation on entity bean
//		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
//		MappingJacksonValue mapping = new MappingJacksonValue(user);
//		mapping.setFilters(filters);
//		return mapping;
//	}
//
//	@GetMapping(path = "/users-filter", produces = { "application/json", "application/xml" })
//	public MappingJacksonValue getAllFilterUsers() {
//		List<User> users = userService.findAllUser();
//
//		// it will ignore all the fields apart from id and name
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
//
//		// have to add @JsonFilter("UserFileter") annotation on entity bean
//		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
//		MappingJacksonValue mapping = new MappingJacksonValue(users);
//		mapping.setFilters(filters);
//		return mapping;
//	}

}
