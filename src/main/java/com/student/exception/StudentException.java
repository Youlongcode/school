package com.student.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义统一异常处理
 * @author Administrator
 *
 */
public class StudentException extends RuntimeException{

	private static final long serialVersionUID = 7278636172281449945L;

	private static final Logger logger = LoggerFactory.getLogger(StudentException.class);
	
}
