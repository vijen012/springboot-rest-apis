package com.restful.user.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.restful.user.data.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	public List<User> findByFirstNameAndLastName(String firstName, String lastName);

	public List<User> findByBirthDate(LocalDate birthDate);

	public List<User> findByEmailLike(String emailPattern);
}
