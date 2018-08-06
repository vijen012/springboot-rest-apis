package com.springboot.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.springboot.domain.Topic;
import com.springboot.repository.TopicRepository;
import com.springboot.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	private TopicRepository topicRepository;

	@Override
	public List<Topic> getAllTopics(String courseId) {	
		List<Topic> topics = new ArrayList<Topic>();
		topicRepository.findByCourseId(courseId).forEach(topics:: add);
		return topics;
	}

	@Override
	public Topic getTopic(String id) {
		try {
			return topicRepository.findOne(id);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Exception Class: " + e.getClass() + "Exception Message: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
	}

	@Override
	public Topic updateTopic(Topic topic, String id) {
		return topicRepository.save(topic);
	}

	@Override
	public boolean deleteTopic(String id) {
		try {
			topicRepository.delete(id);
			return true;
		}
		catch(IllegalArgumentException | EmptyResultDataAccessException e) {
			System.out.println("Exception Class: " + e.getClass() + "Exception Message: " + e.getMessage());
			return false;
		}
	}
}
