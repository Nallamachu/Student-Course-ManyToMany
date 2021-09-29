package com.enrolment.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.enrolment.student.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query(value = "select * from student s where s.id not in (select distinct sc.student_id from student_customer sc)", nativeQuery = true)
	public List<Student> getStudentsWithoutAnyCourse();
}
