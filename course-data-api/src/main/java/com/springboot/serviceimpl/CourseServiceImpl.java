package com.springboot.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.springboot.domain.Course;
import com.springboot.repository.CourseRepository;
import com.springboot.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public List<Course> getAllCourses() {	
		List<Course> courses = new ArrayList<Course>();
		courseRepository.findAll().forEach(courses:: add);
		return courses;
	}

	@Override
	public Course getCourse(String id) {
		try {
			return courseRepository.findOne(id);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Exception Class: " + e.getClass() + "Exception Message: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course updateCourse(Course course, String id) {
		return courseRepository.save(course);
	}

	@Override
	public boolean deleteCourse(String id) {
		try {
			courseRepository.delete(id);
			return true;
		}
		catch(IllegalArgumentException | EmptyResultDataAccessException e) {
			System.out.println("Exception Class: " + e.getClass() + "Exception Message: " + e.getMessage());
			return false;
		}
	}
}
