package com.enrolment.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enrolment.student.entities.Course;
import com.enrolment.student.entities.Student;
import com.enrolment.student.repository.CourseRepository;
import com.enrolment.student.repository.StudentRepository;

@Service
public class CourseServiceImpl implements CourseService {

	private static final int STUDENTS_LIMIT_PER_COURSE = 50;
	
	@Autowired
	public CourseRepository courseRepository;
	
	@Autowired
	public StudentRepository studentRepository;

	@Override
	public Course addNewCourse(Course course) {
		if (course != null && course.getStudents() != null) {
			if(course.getStudents().size() > STUDENTS_LIMIT_PER_COURSE) {
				return null;
			}
		}
		return courseRepository.save(course);
	}

	@Override
	public Course getCourseById(long id) {
		Optional<Course> optStudent = courseRepository.findById(id);
		if (optStudent.isPresent()) {
			return optStudent.get();
		}
		return null;
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course updateCourse(Course course) {
		if (course != null && course.getStudents() != null) {
			Course dbCourse = courseRepository.getById(course.getId());
			if(dbCourse !=null) {
				course.getStudents().addAll(dbCourse.getStudents());
				if(course.getStudents().size() > STUDENTS_LIMIT_PER_COURSE) {
					return null;
				}
			}
		}
		return courseRepository.saveAndFlush(course);
	}

	@Override
	public void deleteCourse(long id) {
		Course course = courseRepository.getById(id);
		if (course != null) {
			courseRepository.delete(course);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesByStudent(Long studentId) {
		Optional<Student> optStudent = studentRepository.findById(studentId);
		if (optStudent.isPresent()) {
			return (List<Course>) optStudent.get().getCourses();
		}
		return null;
	}

	@Override
	public List<Course> getCoursesWithoutAnyStudents() {
		return courseRepository.getCoursesWithoutAnyStudents();
	}

}
