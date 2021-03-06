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
	
	/**
	 * 扩展查询，根据年龄查询数据
	 * <br><br>
	 * <b>如果需要根据某个或N个字段查询数据，只需在接口中添加相应的方法即可，注意：方法名需要规范 </b>
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
	 * @param student
	 * @param bindingResult 获取错误信息
	 * @return 学生对象
	 * 
	 * 添加一位学生<br>
	 * 如果一次添加的属性多，可以直接传一个对象就行<br>
	 * {@valid} 添加验证<br>
	 * 将返回对象改成Object对象：如果失败页面将直接输出失败信息，否则直接返回个json对象。这样的话格式就不统一，给用户提供接口时，
	 * 客户端处无法返回对象。<br>
	 * 解决方法：创建个结果返回类,将数据封装，达到格式的统一（Result）	根据实际情况，传递相应的参数：code"错误码",
	 * msg"提示信息",data"具体的内容（对象）"<br>
	 * 
	 * 如果在写程序时，发现代码有重复的，要立刻优化，如：写个utils(工具类)
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/students/add")
	public Result<Student> studentAdd(@Valid Student student,BindingResult bindingResult) {
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
	 * 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/students/{id}")
	public Student findByStudent(@PathVariable("id") Integer id) throws Exception {
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
