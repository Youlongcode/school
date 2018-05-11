package com.student.uitls;

import com.student.domain.Result;
import com.student.domain.Student;

public class ResultUitl {
	
	@SuppressWarnings("all")
	public static Result<Student> success(Object object){
		Result result = new Result();
		result.setCode(0);
		result.setMsg("成功！");
		result.setData(object);
		return result;
	}
	
	public static Result<Student> success(){
		return success(null);
	}
	
	@SuppressWarnings("rawtypes")
	public static Result error(Integer code,String msg){
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
