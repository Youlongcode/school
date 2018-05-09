package com.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.domain.Result;
import com.student.domain.Student;
import com.student.service.StudentService;
import com.student.uitls.ResultUitl;
/**
 * 学生controller:控制层
 * 		Web层,对应mvc模式中的c,负责请求转发的功能：接受页面过来的参数，传给service处理，接到返回值，再传给页面
 * 		一个controller可以调用多个service
 * @author Administrator
 *
 */
@RestController
public class StudentController {

	//用logger日志的类型打印出结果
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	private StudentService service;
	
	/*	如果需要根据某个或N个字段查询数据，只需在接口中添加相应的方法即可，注意：方法名需要规范*/
	/**
	 * 扩展查询，根据年龄查询数据
	 * @param age
	 * @return
	 */
	@GetMapping(value="/students/age/{age}")
	public List<Student> findByAge(@PathVariable("age") Integer age){
		return service.findByAge(age);
	}
	
	/**
	 * 查询学生列表
	 * @return
	 */
	@GetMapping(value="/students")
	public List<Student> studentList(){
		logger.info("StudentController.studentList()具体的方法");
		return service.studentList();
	}
	
	/**
	 * 添加一位学生
	 * 如果一次添加的属性多，可以直接传一个对象就行
	 * {@valid} 添加验证
	 * @param student
	 * @param bindingResult 获取错误信息
	 * @return 学生对象
	 */
	@PostMapping(value = "/students/add")
	public Result<Object> studentAdd(@Valid Student student,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResultUitl.error(1, bindingResult.getFieldError().getDefaultMessage());
		}
		student.setAddress(student.getAddress());
		student.setAge(student.getAge());
		student.setGender(student.getGender());
		student.setName(student.getName());
		
		return ResultUitl.success(service.studentByAdd(student));
	}
	
	/**
	 * 查询一个学生信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/students/{id}")
	public Object findByStudent(@PathVariable("id") Integer id) throws Exception {
		return service.findByStudent(id);
	}
	
	/**
	 * 更新一个学生信息
	 * @param id
	 * @param name
	 * @param gender
	 * @param age
	 * @param address
	 * @return
	 */
	@PutMapping(value="/students/{id}")
	public Student updataByStudent(@PathVariable("id") Integer id,
			@RequestParam("name") String name,
			@RequestParam("gender") Integer gender,
			@RequestParam("age") Integer age,
			@RequestParam("address") String address) {
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setGender(gender);
		student.setAge(age);
		student.setAddress(address);
		return service.updataByStudent(student);
	}
	
	/**
	 * 删除一条学生信息
	 * @param id
	 */
	@DeleteMapping(value="/students/{id}")
	public void deleteByStudent(@PathVariable("id") Integer id) {
		service.deleteByStudent(id);
	}
	
	@PostMapping(value="/students/two")
	public  void studentTwo() {
		service.insertTwo();
	}
}
