package com.springboot.service;

import java.util.List;

import com.springboot.domain.Course;

public interface CourseService {
	public List<Course> getAllCourses();
	public Course getCourse(String id);
	public Course addCourse(Course course);
	public Course updateCourse(Course course, String id);
	public boolean deleteCourse(String id);
}
