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
public class StudentServiceImpl implements StudentService {

	private static final int COURSE_LIMIT_PER_STUDENT = 5;

	@Autowired
	public StudentRepository studentRepository;

	@Autowired
	public CourseRepository courseRepository;

	@Override
	public Student addNewStudent(Student student) {
		if (student != null && student.getCourses() != null) {
			if(student.getCourses().size() > COURSE_LIMIT_PER_STUDENT) {
				return null;
			}
		}
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(long id) {
		Optional<Student> optStudent = studentRepository.findById(id);
		if (optStudent.isPresent()) {
			return optStudent.get();
		}
		return null;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student updateStudent(Student student) {
		if (student != null && student.getCourses() != null) {
			Student dbStudent = studentRepository.getById(student.getId());
			if(dbStudent !=null) {
				student.getCourses().addAll(dbStudent.getCourses());
				if(student.getCourses().size() > COURSE_LIMIT_PER_STUDENT) {
					return null;
				}
			}
		}
		return studentRepository.saveAndFlush(student);
	}

	@Override
	public void deleteStudent(long id) {
		Student student = studentRepository.getById(id);
		if (student != null) {
			studentRepository.delete(student);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentByCourse(String courseName) {
		Course course = courseRepository.getCourseByName(courseName);
		if (course == null) {
			return null;
		}
		return (List<Student>) course.getStudents();
	}

	@Override
	public List<Student> getStudentsWithoutAnyCourses() {
		return studentRepository.getStudentsWithoutAnyCourse();
	}
}
