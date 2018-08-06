package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.domain.Course;
import com.springboot.service.CourseService;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public List<Course> getAllCourses() {
		return courseService.getAllCourses(); 
	}
	
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
	public Course getCourse(@PathVariable String id) {
		return courseService.getCourse(id);
	}
	
/*	@RequestMapping(value = "/courses/{courseId}", method = RequestMethod.GET)
	public Course getCourse(@PathVariable("courseId") String id) {
		return courseService.getCourse(id);
	}	*/
	
	@RequestMapping(value = "/courses", method = RequestMethod.POST)
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		return new ResponseEntity<Course>(courseService.addCourse(course), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/courses/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable String id) {
		return new ResponseEntity<Course>(courseService.updateCourse(course, id), HttpStatus.OK);
	}

	@RequestMapping(value = "/courses/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
		HttpStatus httpStatus = courseService.deleteCourse(id) ? HttpStatus.NO_CONTENT : HttpStatus.ACCEPTED;
		return new ResponseEntity<Void>(httpStatus);
	}
	
}
