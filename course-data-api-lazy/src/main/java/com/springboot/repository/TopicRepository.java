package com.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.springboot.domain.Topic;

public interface TopicRepository extends CrudRepository<Topic, String>{
	//return all the topics which are matches with provided topic name
	public List<Topic> findByName(String name);
	
	//return all the topics which are matches with provided topic id
	public List<Topic> findById(String id);
	
	//Return all the topics which are matches with Course Property propert id(Topic.course.id)
	public List<Topic> findByCourseId(String courseId);
}
