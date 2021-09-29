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

import com.enrolment.student.entities.Student;
import com.enrolment.student.entities.StudentDTO;
import com.enrolment.student.service.StudentService;

@RestController
@RequestMapping(path = "/v1/api")
public class StudentController {

	@Autowired
	public StudentService studentService;

	ModelMapper mapper = new ModelMapper();

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/all-students", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StudentDTO> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return (List<StudentDTO>) mapper.map(students, StudentDTO.class);
	}

	@GetMapping(path = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public StudentDTO getStudentById(@PathVariable(name = "id") Long id) {
		if (id == null)
			return null;
		Student student = studentService.getStudentById(id);
		return mapper.map(student, StudentDTO.class);
	}

	@PostMapping(path = "/new-student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public StudentDTO addNewStudent(@RequestBody StudentDTO studentDTO) {
		if (studentDTO == null) {
			return null;
		}
		Student student = mapper.map(studentDTO, Student.class);
		student = studentService.addNewStudent(student);
		return mapper.map(student, StudentDTO.class);
	}

	@PutMapping(path = "/update-student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO) {
		if (studentDTO == null)
			return null;

		Student student = mapper.map(studentDTO, Student.class);
		student = studentService.updateStudent(student);
		return (student != null) ? mapper.map(student, StudentDTO.class) : null;
	}

	@DeleteMapping(path = "/delete-student/{id}")
	public void deleteStudent(@PathVariable(name = "id") long id) {
		studentService.deleteStudent(id);
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/course-without-student", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StudentDTO> getStudentsWithoutAnyStudents() {
		List<Student> students = studentService.getStudentsWithoutAnyCourses();
		return (students != null) ? (List<StudentDTO>) mapper.map(students, StudentDTO.class) : null;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/student-by-course/{courseName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StudentDTO> getStudentsByCourse(@PathVariable(name = "courseName") String courseName) {
		List<Student> students = studentService.getStudentByCourse(courseName);
		return (students != null) ? (List<StudentDTO>) mapper.map(students, StudentDTO.class) : null;
	}

}
