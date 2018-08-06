package com.springboot.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * The persistent class for the TOPIC database table.
 * 
 */
@Entity
@Table(name="TOPIC")
@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t")
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String id;

	@Column(length=50)
	private String description;

	@Column(length=30)
	private String name;

	//bi-directional many-to-one association to Course
	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="COURSE_ID")
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
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}