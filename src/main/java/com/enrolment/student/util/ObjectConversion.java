package com.enrolment.student.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.enrolment.student.entities.Course;
import com.enrolment.student.entities.CourseDTO;
import com.enrolment.student.entities.Student;
import com.enrolment.student.entities.StudentDTO;

public class ObjectConversion {
	static ModelMapper mapper = new ModelMapper();
	
	public List<StudentDTO> studentEntityToDTOList(List<Student> students){
		List<StudentDTO> studentDTOs = new ArrayList<>();
		if(students !=null) {
			for (Student student : students) {
				StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
				studentDTOs.add(studentDTO);
			}
		}
		return studentDTOs;
	}
	
	public StudentDTO studentEntityToDTO(Student student) {
		return mapper.map(student, StudentDTO.class);
	}
	
	public Student studentDTOToEntity(StudentDTO studentDTO) {
		return mapper.map(studentDTO, Student.class);
	}
	
	public List<Student> studentDTOToEntityList(List<StudentDTO> studentDTOs){
		List<Student> students = new ArrayList<>();
		if(studentDTOs !=null) {
			for (StudentDTO studentDTO : studentDTOs) {
				Student student = mapper.map(studentDTO, Student.class);
				students.add(student);
			}
		}
		return students;
	}
	
	
	public List<CourseDTO> courseEntityToDTOList(List<Course> courses){
		List<CourseDTO> courseDTOs = new ArrayList<>();
		if(courses !=null) {
			for (Course course : courses) {
				CourseDTO courseDTO = mapper.map(course, CourseDTO.class);
				courseDTOs.add(courseDTO);
			}
		}
		return courseDTOs;
	}
	
	public CourseDTO courseEntityToDTO(Course course) {
		return mapper.map(course, CourseDTO.class);
	}
	
	public Course courseDTOToEntity(CourseDTO courseDTO) {
		return mapper.map(courseDTO, Course.class);
	}
	
	public List<Course> courseDTOToEntityList(List<CourseDTO> courseDTOs){
		List<Course> courses = new ArrayList<>();
		if(courseDTOs !=null) {
			for (CourseDTO courseDTO : courseDTOs) {
				Course course = mapper.map(courseDTO, Course.class);
				courses.add(course);
			}
		}
		return courses;
	}

}
