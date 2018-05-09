package com.student.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 添加学生AOP（切面）处理请求
 * @author Administrator
 *
 */
@Aspect
@Component
public class HttpAspect {

	//用logger日志的类型打印出结果
	private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	
	/**
	 * 需要切入的类或方法路径
	 */
	@Pointcut("execution(* com.student.controller.StudentController.*(..))") 
	public void log() {
		
	}
	
	/**
	 * 方法执行前切入
	 * @param joinPoint
	 */
	@Before("log()")	
	public void before(JoinPoint joinPoint) {
		//logger.info("@Before学生AOP处理请求");
		
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		//获取请求的URL
		logger.info("URL={}",request.getRequestURL());
		//获取调用的method
		logger.info("method={}",request.getMethod());
		//获取ip
		logger.info("ip={}",request.getRemoteAddr());
		//获取类方法
		logger.info("class-method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
		//获取参数
		logger.info("args={}",joinPoint.getArgs());
	}
	
	/**
	 * 方法执行后切入
	 */
	@After("log()")		
	public void after() {
		logger.info("@After学生AOP处理请求");
	}
	
	/**
	 * 获取返回的数据
	 * @param object 返回的数据可能很多，但对程序而言，都是一个对象
	 */
	@AfterReturning(returning="object",pointcut="log()")
	public void afterReturning(Object object) {
		logger.info("response={}",object.toString());
	}
}
