package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.domain.Student;
import com.student.repository.StudentRepository;

/**
 * 学生的service：业务逻辑层（对就mvc模式中的m）
 * @author Administrator
 *
 */
@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	@Transactional //事务注解，判断如果插入数据有错误，就不能添加到数据库
	public void insertTwo() {
		Student studentA = new Student();
		studentA.setAddress("中国河南");
		studentA.setAge(22);
		studentA.setGender(1);
		studentA.setName("张三");
		repository.save(studentA);
		
		Student studentB = new Student();
		studentB.setAddress("中国河南");
		studentB.setAge(22);
		studentB.setGender(1);
		studentB.setName("张三");
		repository.save(studentB);
	}
	
	public List<Student> findByAge(Integer age){
		return repository.findByAge(age);
	}
	
	public List<Student> studentList(){
		return repository.findAll();
	}
	
	public Student studentByAdd(Student student) {
		Integer gender = student.getGender();
		if (gender<0||gender>2) {
			System.out.println("无法识别的性别！");
			return null;
		}
		return repository.save(student);
	}
	
	public Object findByStudent(Integer id) throws Exception {
		Student student = repository.getOne(id);
		Integer age = student.getAge();
		if (age<12) {
			//返回你还在上小学吧
			throw new Exception("你还在上小学吧!");
		}else if (age>12&&age<16) {
			//返回你还在上初中吧
			throw new Exception("你还在上初中吧!");
		}
		return  repository.findById(id);
	}
	
	public Student updataByStudent(Student student) {
		return repository.save(student);
	}
	
	public void deleteByStudent(Integer id) {
		repository.deleteById(id);
	}
}
