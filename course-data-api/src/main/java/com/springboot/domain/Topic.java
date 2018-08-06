package com.springboot.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Topic implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String description;
	
//	@ManyToOne(fetch = FetchType.LAZY) // fetch = FetchType.LAZY -  it will prevent topic object from appearing in json	
	@ManyToOne
	private Course course;
	
	public Topic() {
		
	}
	
	public Topic(String id, String name, String description, Course course) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.course = new Course(course.getId(), "", "");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
