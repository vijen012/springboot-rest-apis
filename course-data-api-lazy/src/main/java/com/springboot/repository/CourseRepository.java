package com.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.domain.Course;

public interface CourseRepository extends CrudRepository<Course, String>{
	
}
