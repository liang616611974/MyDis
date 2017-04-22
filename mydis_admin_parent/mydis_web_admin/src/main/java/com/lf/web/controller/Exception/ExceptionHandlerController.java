package com.lf.web.controller.Exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
* <p>Title: ExceptionHandlerController.java<／p>
* <p>Description: 异常处理控制器<／p>
* @author Liangfeng
* @date 2017-1-10
* @version 1.0
 */
@ControllerAdvice
public class ExceptionHandlerController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
	
	@ExceptionHandler(value = { ArithmeticException.class })
	public String ArithmeticException(Exception e, HttpServletRequest request) {
		logger.error("算法有错",e);
		request.setAttribute("exception", e);
		return "/error/error";
	}
	
	@ExceptionHandler(value = { Exception.class })
	public String exception(Exception e, HttpServletRequest request) {
		logger.error("访问controller报错",e);
		request.setAttribute("exception", e);
		return "/error/error";
	}

	

}
