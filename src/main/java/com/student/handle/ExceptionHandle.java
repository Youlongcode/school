package com.student.handle;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.domain.Result;
import com.student.uitls.ResultUitl;

/**
 * 异常捕获
 * @author Administrator
 *
 */
@ControllerAdvice
public class ExceptionHandle {

	@SuppressWarnings("all")
	@ExceptionHandler(value=Exception.class)	//声明需要捕获哪个类的异常。
	@ResponseBody								//因为返回到页面的是json对象，所以需要加这个注释。
	public Result handle(Exception e){
		return ResultUitl.error(100, e.getMessage());
	} 
}
