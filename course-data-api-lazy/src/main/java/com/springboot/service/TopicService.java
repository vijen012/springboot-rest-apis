package com.springboot.service;

import java.util.List;

import com.springboot.domain.Topic;

public interface TopicService {
	public List<Topic> getAllTopics(String id);
	public Topic getTopic(String id);
	public Topic addTopic(Topic topic);
	public Topic updateTopic(Topic topic, String id);
	public boolean deleteTopic(String id);
}
