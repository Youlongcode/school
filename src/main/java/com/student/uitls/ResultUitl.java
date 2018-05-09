package com.student.uitls;

import com.student.domain.Result;

public class ResultUitl {
	
	public static Result<Object> success(Object object){
		Result<Object> result = new Result<>();
		result.setCode(0);
		result.setMsg("成功！");
		result.setData(object);
		return result;
	}
	
	public static Result<Object> success(){
		return success(null);
	}
	
	public static Result<Object> error(Integer code,String msg){
		Result<Object> result = new Result<>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
