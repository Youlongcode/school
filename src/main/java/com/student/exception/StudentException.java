package com.student.exception;

import com.student.eunms.ResultEnum;

/**
 * 自定义统一异常处理
 * <br><br>
 * <b>
 * 注意：spring框架只对抛出的异常是RuntimeException时，才对事务进行回滚，如果直接抛出Exception，是不会对事务进行回滚的
 * </b>
 * @author Administrator
 *
 */
public class StudentException extends RuntimeException{

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public StudentException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.code = resultEnum.getCode();
	}

	private static final long serialVersionUID = 7278636172281449945L;
	
	private Integer code;
	
}
