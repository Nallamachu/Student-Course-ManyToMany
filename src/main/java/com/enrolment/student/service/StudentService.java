package com.enrolment.student.service;

import java.util.List;

import com.enrolment.student.entities.Student;

public interface StudentService {

	public Student addNewStudent(Student student);

	public Student getStudentById(long id);

	public List<Student> getAllStudents();

	public Student updateStudent(Student student);

	public void deleteStudent(long id);

	public List<Student> getStudentByCourse(String courseName);

	public List<Student> getStudentsWithoutAnyCourses();

}
