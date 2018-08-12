package com.restful.user.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.restful.user.data.User;
import com.restful.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * @Value("${accountApiUrl}") private String accountApiUrl;
	 */

	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers(
			@RequestHeader(value = "x-request-header", required = true) @Valid String requestHeader,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize) {
		return new ResponseEntity<List<User>>(userService.findAllUser(pageNumber, pageSize), HttpStatus.OK);
	}

	@GetMapping(path = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
		return new ResponseEntity<User>(userService.findUser(userId), HttpStatus.OK);
	}

	@PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@Valid @RequestBody(required = true) User user) {
		User savedUser = userService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<User>(savedUser, headers, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/users/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		return new ResponseEntity<Void>(userService.deleteUser(userId));
	}

	@PutMapping(path = "/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @Valid @RequestBody(required = true) User user) {
		return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
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
