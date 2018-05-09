package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.domain.Student;

/**
 * 学生DAO层
 * @author Administrator
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	public List<Student> findByAge(Integer age);
}
