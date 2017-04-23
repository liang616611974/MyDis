package com.lf.web.filter.xsssecurity;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* <p>Title: XSSSecurityFilter.java<／p>
* <p>Description: 防御XSS攻击Filter<／p>
* @author Liangfeng
* @date 2017-2-20
* @version 1.0
 */
public class XSSSecurityFilter implements Filter{

	private static final Logger logger = LoggerFactory.getLogger(XSSSecurityFilter.class);
	
	/**
	 * 初始化
	 */
	public void init(FilterConfig config) throws ServletException {
		//1.初始化XSSSecurityManager管理器
        String xssConfigPath = config.getServletContext().getRealPath("/")
        		+ config.getInitParameter("securityconfig");
        try{
        	XSSSecurityManager.init(xssConfigPath);
        }catch (Exception e) {
        	logger.error("初始化XSSSecurityManager管理器发生异常",e);
        	throw new RuntimeException("初始化XSSSecurityManager管理器发生异常",e);
		}
	}
	
	/**
	 * 拦截处理
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 1.判断是否使用HTTP
		checkRequestResponse(request, response);
		// 2.创建XSSHttpRequestWrapper请求包装类
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		XSSHttpRequestWrapper xssRequest = new XSSHttpRequestWrapper(httpRequest);
		// 3.对请求信息进行校验
		if(!xssRequest.validateParameter()){
			// 3.1记录攻击访问日志
			if(XSSSecurityConfig.IS_LOG){
        		String ip = xssRequest.getRemoteAddr();
        		ip=ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
        		logger.info("攻击者IP地址：{}",ip);
        		// 可使用数据库、日志、文件等方式
        	}
			// 3.2是否终止该访问
			if(XSSSecurityConfig.IS_CHAIN){
				//返回非法请求状态码
				httpResponse.setStatus(400);
        		return;
    		}
			
		}
		// 4.放行
		chain.doFilter(xssRequest, response);
	}
	
	/**
	 * 销毁
	 */
	public void destroy() {
		// XSSSecurityManager管理器注销
		XSSSecurityManager.destroy();
	}


	
	
	
	/**
	 * 检查是否HTTP请求
	 * @param request
	 * @param response
	 * @throws ServletException 
	 */
	private void checkRequestResponse(ServletRequest request,ServletResponse response) throws ServletException{
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("HttpServletRequest");
        }
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("Can only process HttpServletResponse");
        }
    }

}
