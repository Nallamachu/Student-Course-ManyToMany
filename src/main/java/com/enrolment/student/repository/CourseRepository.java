package com.enrolment.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.enrolment.student.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query(value = "from Course where name=?1", nativeQuery = false)
	public Course getCourseByName(String courseName);

	@Query(value = "select * from course c where c.id not in (select distinct sc.course_id from student_customer sc)", nativeQuery = true)
	public List<Course> getCoursesWithoutAnyStudents();

}
