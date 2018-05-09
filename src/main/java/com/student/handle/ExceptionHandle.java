package com.student.handle;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.student.domain.Result;
import com.student.uitls.ResultUitl;
@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Result<Object> handle(Exception e){
		return ResultUitl.error(100, e.getMessage());
	} 
}
