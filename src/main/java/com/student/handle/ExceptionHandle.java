package com.student.handle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.domain.Result;
import com.student.exception.StudentException;
import com.student.uitls.ResultUitl;

/**
 * 异常捕获
 * @author Administrator
 *
 */
@ControllerAdvice
public class ExceptionHandle {
	
	private static final Logger logger = LoggerFactory.getLogger(Exception.class);

	@SuppressWarnings("all")
	@ExceptionHandler(value=Exception.class)	//声明需要捕获哪个类的异常。
	@ResponseBody								//因为返回到页面的是json对象，所以需要加这个注释。
	public Result handle(Exception e){
		if (e instanceof StudentException) {
			StudentException studentException = (StudentException) e;
			return ResultUitl.error(studentException.getCode(), studentException.getMessage());
		}else {
			logger.error("【系统异常】{}",e); //控制台打印出未知错误的日志
			return ResultUitl.error(-1, "未知错误！");
		}
	} 
}
