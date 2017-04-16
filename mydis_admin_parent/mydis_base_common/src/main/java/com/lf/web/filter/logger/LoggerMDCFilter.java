package com.lf.web.filter.logger;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lf.helper.StringHelper;
import com.lf.helper.UUIDHelper;

/**
 * @Title: LoggerMDCFilter.java
 * @Description: 存放在MDC中的数据，logback可以直接引用并作为日志信息打印出来. 示例：%X{userId}
 * @author Liangfeng
 * @date 2016-10-27
 * @version 1.0
 */
public class LoggerMDCFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(LoggerMDCFilter.class);
	
	private static final String USER_ID_KEY_SESSION = "userId";
	private static final String USER_ID_KEY = "userId";
	private static final String REQUEST_URI_KEY = "requestURI";
	private static final String REMOTE_ADDR_KEY = "remoteAddr";
	private static final String UUID_KEY = "uuid";
	private static final String PREFIX = "(";
	private static final String SUFFIX = ")";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain){
		try{
			//1.加入当前登陆用户id
			String userId = USER_ID_KEY + PREFIX + StringHelper.defaultString(String.valueOf(request.getSession().getAttribute(USER_ID_KEY_SESSION))) + SUFFIX;
			MDC.put(USER_ID_KEY, userId);
			//2.加入请求URI
			String requestURI = REQUEST_URI_KEY + PREFIX +StringHelper.defaultString(request.getRequestURI()) + (request.getQueryString() == null ? "" : "?"+request.getQueryString()) + SUFFIX;
			MDC.put(REQUEST_URI_KEY,requestURI);
			//3.加入远程访问地址
			String remoteAddr = REMOTE_ADDR_KEY + PREFIX + StringUtils.defaultString(request.getRemoteAddr()) + SUFFIX;
			MDC.put(REMOTE_ADDR_KEY, remoteAddr);
			//4.为每一个请求创建一个ID，方便查找日志时可以根据ID查找出一个http请求所有相关日志
			String uuid = UUID_KEY + PREFIX + UUIDHelper.generateUUID() + SUFFIX;
            MDC.put(UUID_KEY,uuid); 
            //5.放行
            chain.doFilter(request, response);
		}catch (Exception e) {
			logger.error("存放MDC日志属性发生异常",e);
			throw new RuntimeException("存放MDC日志属性发生异常",e);
		}
	}
}
