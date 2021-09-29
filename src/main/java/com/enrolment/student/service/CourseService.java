package com.enrolment.student.service;

import java.util.List;

import com.enrolment.student.entities.Course;

public interface CourseService {
	public Course addNewCourse(Course Course);

	public Course getCourseById(long id);

	public List<Course> getAllCourses();

	public Course updateCourse(Course Course);

	public void deleteCourse(long id);
	
	public List<Course> getCoursesWithoutAnyStudents();
	
	public List<Course> getCoursesByStudent(Long studentId);
}
