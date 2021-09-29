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

import com.enrolment.student.entities.Student;
import com.enrolment.student.entities.StudentDTO;
import com.enrolment.student.service.StudentService;
import com.enrolment.student.util.ObjectConversion;

@RestController
@RequestMapping(path = "/v1/api/student")
public class StudentController {

	@Autowired
	public StudentService studentService;

	@Autowired
	public ObjectConversion conversion;

	@GetMapping(path = "/all-students", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return (students !=null)?new ResponseEntity<List<StudentDTO>>(conversion.studentEntityToDTOList(students),HttpStatus.OK)
				:new ResponseEntity("Exception while getting all students",HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "id") Long id) {
		if (id == null)
			return null;
		Student student = studentService.getStudentById(id);
		return (student !=null)?new ResponseEntity<StudentDTO>(conversion.studentEntityToDTO(student),HttpStatus.OK)
				:new ResponseEntity("Exception while getting student by id",HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping(path = "/new-student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentDTO> addNewStudent(@RequestBody StudentDTO studentDTO) {
		if (studentDTO == null) {
			return null;
		}
		Student student = conversion.studentDTOToEntity(studentDTO);
		student = studentService.addNewStudent(student);
		return (student !=null)?new ResponseEntity<StudentDTO>(conversion.studentEntityToDTO(student),HttpStatus.OK)
				:new ResponseEntity("Exception while adding new student",HttpStatus.EXPECTATION_FAILED);
	}

	@PutMapping(path = "/update-student", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
		if (studentDTO == null)
			return null;

		Student student = conversion.studentDTOToEntity(studentDTO);
		student = studentService.updateStudent(student);
		return (student !=null)?new ResponseEntity<StudentDTO>(conversion.studentEntityToDTO(student),HttpStatus.OK)
				:new ResponseEntity("Exception while updating student",HttpStatus.EXPECTATION_FAILED);
	}

	@DeleteMapping(path = "/delete-student/{id}")
	public void deleteStudent(@PathVariable(name = "id") long id) {
		studentService.deleteStudent(id);
	}

	@GetMapping(path = "/course-without-student", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentDTO>> getStudentsWithoutAnyStudents() {
		List<Student> students = studentService.getStudentsWithoutAnyCourses();
		return (students !=null)?new ResponseEntity<List<StudentDTO>>(conversion.studentEntityToDTOList(students),HttpStatus.OK)
				:new ResponseEntity("Exception while getting all students",HttpStatus.EXPECTATION_FAILED);
	}

	@GetMapping(path = "/student-by-course/{courseName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentDTO>> getStudentsByCourse(@PathVariable(name = "courseName") String courseName) {
		List<Student> students = studentService.getStudentByCourse(courseName);
		return (students !=null)?new ResponseEntity<List<StudentDTO>>(conversion.studentEntityToDTOList(students),HttpStatus.OK)
				:new ResponseEntity("Exception while getting all students",HttpStatus.EXPECTATION_FAILED);
	}

}
