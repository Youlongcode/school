package com.student.eunms;

/**
 * 枚举使用都是直接用它的构造方法来创建，不会在重新set，所以只需要提供get方法和一个有参的构造方法就行。
 * @author Administrator
 *
 */
public enum ResultEnum {
	
	UNKONW_ERROR(-1,"未知错误！"),
	SUCCESS(0,"成功"),
	PRIMARY_SCHOOL(100,"你还在上小学吧"),
	MIDDLE_SCHOOL(101,"你还在上初中吧"),
	;
	
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	
	private ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	private Integer code;
	private String msg;
	
}
