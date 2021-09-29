package com.enrolment.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.enrolment.student.util.ObjectConversion;

@RestController
@RequestMapping(path = "/v1/api/course")
public class CourseController {

	@Autowired
	public CourseService courseService;

	@Autowired
	public ObjectConversion conversion;

	@GetMapping(path = "/all-courses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CourseDTO>> getAllCourses() {
		List<Course> courses = courseService.getAllCourses();
		if(courses != null) {
			List<CourseDTO> courseDTOs = conversion.courseEntityToDTOList(courses);
			return new ResponseEntity<List<CourseDTO>>(courseDTOs, HttpStatus.OK);
		}
		return new ResponseEntity("Exception while getting all courses", HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourseDTO> getCourseById(@PathVariable(name = "id") Long id) {
		if (id == null)
			return null;
		Course course = courseService.getCourseById(id);
		return (course != null) ? new ResponseEntity<CourseDTO>(conversion.courseEntityToDTO(course),HttpStatus.OK) 
				: new ResponseEntity("Exception while getting course by id", HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping(path = "/new-course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourseDTO> addNewCourse(@RequestBody CourseDTO courseDTO) {
		if (courseDTO == null) {
			return null;
		}
		Course course = conversion.courseDTOToEntity(courseDTO);
		course = courseService.addNewCourse(course);
		return (course != null) ? new ResponseEntity<CourseDTO>(conversion.courseEntityToDTO(course),HttpStatus.OK) 
				: new ResponseEntity("Exception while getting course by id", HttpStatus.EXPECTATION_FAILED);
	}

	@PutMapping(path = "/update-course", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO) {
		if (courseDTO == null)
			return null;

		Course course = conversion.courseDTOToEntity(courseDTO);
		course = courseService.updateCourse(course);
		return (course != null) ? new ResponseEntity<CourseDTO>(conversion.courseEntityToDTO(course),HttpStatus.OK) 
				: new ResponseEntity("Exception while updating the course", HttpStatus.EXPECTATION_FAILED);
	}

	@DeleteMapping(path = "/delete-course/{id}")
	public void deleteCourse(@PathVariable(name = "id") long id) {
		courseService.deleteCourse(id);
	}

	@GetMapping(path = "/course-without-student", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CourseDTO>> getCoursesWithoutAnyStudents() {
		List<Course> courses = courseService.getCoursesWithoutAnyStudents();
		if(courses !=null) {
			List<CourseDTO> courseDTOs = conversion.courseEntityToDTOList(courses);
			return new ResponseEntity<List<CourseDTO>>(courseDTOs, HttpStatus.OK);
		}
		return new ResponseEntity("Exception While getting the courses wihout students", HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping(path = "/course-by-student", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CourseDTO>> getCoursesByStudent(Long studentId) {
		List<Course> courses = courseService.getCoursesByStudent(studentId);
		return (courses != null) ? new ResponseEntity<List<CourseDTO>>(conversion.courseEntityToDTOList(courses),HttpStatus.OK) 
				: new ResponseEntity("Exception while getting course by id", HttpStatus.EXPECTATION_FAILED);
	}

}
