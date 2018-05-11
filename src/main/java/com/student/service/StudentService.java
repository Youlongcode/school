package com.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.domain.Student;
import com.student.repository.StudentRepository;

/**
 * 学生的service：业务逻辑层（对就mvc模式中的m）
 *
 * 具体的业务逻辑（各种条件），功能处理
 * @author Administrator
 *
 */
@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	@Transactional //事务注解，判断如果插入数据有错误，就不能添加到数据库。例如：银行存，取钱系统，任何一个操作失败，两个都的回流。
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
	
	/**
	 * 如果返回对象是String，会将一段字符串返回到页面，这样只是简单的判断年龄，后续的一些业务功能将无法得到现实，例如：不同年龄段，有不同的课程。
	 * 如果返回对象是Integer，用数字来表示的话,return:0 成功，1 小学，2初中。这样做的话，少的话还好，如果需要判断的多了，就比较麻烦，
	 * 		且在controller中调用时，还需要在判断一次，这样做就比较麻烦了。这时就可以用到统一异常处理
	 * @param id
	 * @throws Exception
	 */
	public void findByStudent(Integer id) throws Exception {
		Student student = repository.getOne(id);
		Integer age = student.getAge();
		if (age<12) {
			throw new Exception("你还在上小学吧!");	//返回你还在上小学吧
		}else if (age>12&&age<16) {
			throw new Exception("你还在上初中吧!");	//返回你还在上初中吧
		}
	}
	
	public Student updataByStudent(Student student) {
		return repository.save(student);
	}
	
	public void deleteByStudent(Integer id) {
		repository.deleteById(id);
	}
}
