package com.enrolment.student;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.enrolment.student.entities.Course;
import com.enrolment.student.entities.Student;
import com.enrolment.student.repository.CourseRepository;
import com.enrolment.student.repository.StudentRepository;

@SpringBootApplication
public class StudentCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(StudentRepository studentRepository, CourseRepository courseRepository) {
		return args -> {
			Student student = new Student("Ramani", "Nallamachu");
			student = studentRepository.save(student);

			Course course1 = new Course("Machine Learning");
			Course course2 = new Course("Database Systems");
			Course course3 = new Course("Web Basics");
			Course course4 = new Course("Java");
			Course course5 = new Course("Artificial Intelligence");
			Course course6 = new Course(".Net");
			Course course7 = new Course("C");
			Course course8 = new Course("Big Data");

			courseRepository
					.saveAll(Arrays.asList(course1, course2, course3, course4, course5, course6, course7, course8));

			student.getCourses().addAll(Arrays.asList(course1, course2, course3, course4));
			studentRepository.save(student);
		};
	}

}
