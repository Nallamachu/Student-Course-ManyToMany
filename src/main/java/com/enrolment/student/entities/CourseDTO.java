package com.enrolment.student.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CourseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private Set<Student> students = new HashSet<>();

	public CourseDTO() {

	}

	public CourseDTO(long id, String name, Set<Student> students) {
		super();
		this.id = id;
		this.name = name;
		this.students = students;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
