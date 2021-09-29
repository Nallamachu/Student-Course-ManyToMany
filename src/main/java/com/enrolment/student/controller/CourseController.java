package com.enrolment.student.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enrolment.student.entities.Course;
import com.enrolment.student.entities.CourseDTO;
import com.enrolment.student.service.CourseService;

@RestController
@RequestMapping(path = "/v1/api/course")
public class CourseController {

	@Autowired
	public CourseService courseService;

	ModelMapper mapper = new ModelMapper();

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/all-courses", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CourseDTO> getAllCourses() {
		List<Course> courses = courseService.getAllCourses();
		return (courses !=null)?(List<CourseDTO>) mapper.map(courses, CourseDTO.class):null;
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CourseDTO getCourseById(@PathVariable(name = "id") Long id) {
		if (id == null)
			return null;
		Course course = courseService.getCourseById(id);
		return (course != null) ? mapper.map(course, CourseDTO.class) : null;
	}

	@PostMapping(path = "/new-course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CourseDTO addNewCourse(@RequestBody CourseDTO courseDTO) {
		if (courseDTO == null) {
			return null;
		}
		Course course = mapper.map(courseDTO, Course.class);
		course = courseService.addNewCourse(course);
		return (course != null) ? mapper.map(course, CourseDTO.class) : null;
	}

	@PutMapping(path = "/update-course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO) {
		if (courseDTO == null)
			return null;

		Course course = mapper.map(courseDTO, Course.class);
		course = courseService.updateCourse(course);
		return (course != null) ? mapper.map(course, CourseDTO.class) : null;
	}

	@DeleteMapping(path = "/delete-course/{id}")
	public void deleteCourse(@PathVariable(name = "id") long id) {
		courseService.deleteCourse(id);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/course-without-student",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CourseDTO> getCoursesWithoutAnyStudents(){
		List<Course> courses = courseService.getCoursesWithoutAnyStudents();
		return (courses !=null)?(List<CourseDTO>) mapper.map(courses, CourseDTO.class):null;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/course-by-student",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CourseDTO> getCoursesByStudent(Long studentId){
		List<Course> courses = courseService.getCoursesByStudent(studentId);
		return (courses !=null)?(List<CourseDTO>) mapper.map(courses, CourseDTO.class):null;
	}

}
