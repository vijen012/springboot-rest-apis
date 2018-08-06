package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.domain.Course;
import com.springboot.domain.Topic;
import com.springboot.service.TopicService;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping(value = "/courses/{id}/topics", method = RequestMethod.GET)
	public List<Topic> getAllTopics(@PathVariable String id) {
		return topicService.getAllTopics(id); 
	}
	
	@RequestMapping(value = "/courses/{courseId}/topics/{id}", method = RequestMethod.GET)
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
/*	@RequestMapping(value = "/topics/{topicId}", method = RequestMethod.GET)
	public Topic getTopic(@PathVariable("topicId") String id) {
		return topicService.getTopic(id);
	}	*/
	
	@RequestMapping(value = "/courses/{courseId}/topics", method = RequestMethod.POST)
	public ResponseEntity<Topic> addTopic(@RequestBody Topic topic, @PathVariable String courseId) {
		topic.setCourse(new Course(courseId, "", ""));
		return new ResponseEntity<Topic>(topicService.addTopic(topic), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/courses/{courseId}/topics/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Topic> updateTopic(@RequestBody Topic topic, @PathVariable String courseId, @PathVariable String id) {
		topic.setCourse(new Course(courseId, "", ""));
		return new ResponseEntity<Topic>(topicService.updateTopic(topic, id), HttpStatus.OK);
	}

	@RequestMapping(value = "/courses/{courseId}/topics/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTopic(@PathVariable String id) {
		HttpStatus httpStatus = topicService.deleteTopic(id) ? HttpStatus.NO_CONTENT : HttpStatus.ACCEPTED;
		return new ResponseEntity<Void>(httpStatus);
	}
	
}
