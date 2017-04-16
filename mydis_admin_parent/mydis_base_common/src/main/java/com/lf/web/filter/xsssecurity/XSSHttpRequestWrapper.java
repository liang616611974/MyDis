package com.lf.web.filter.xsssecurity;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
* <p>Title: XSSHttpRequestWrapper.java<／p>
* <p>Description: xss防御reqeust包装类，用于判断、处理request请求中特殊字符<／p>
* @author Liangfeng
* @date 2017-2-21
* @version 1.0
 */
public class XSSHttpRequestWrapper extends HttpServletRequestWrapper{
	
	/**
	 * 初始化构造函数
	 * @param request
	 */
	public XSSHttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		// 若开启特殊字符替换，对特殊字符进行替换
		if(XSSSecurityConfig.IS_REPLACE){
			return XSSSecurityManager.securityReplace(value);
		}
		return value;
	}
	
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		// 若开启特殊字符替换，对特殊字符进行替换
		if(XSSSecurityConfig.IS_REPLACE){
			return XSSSecurityManager.securityReplace(value);
		}
		return value;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		// 若开启特殊字符替换，对特殊字符进行替换
		if (values!=null&&XSSSecurityConfig.IS_REPLACE) {
			for (int i = 0; i < values.length; i++) {
				values[i] = XSSSecurityManager.securityReplace(values[i]);
			}
		}
		return values;
	}
	
	/**
	 * 验证参数是否合法
	 * @return 合法返回true,否则返回false
	 */
	public boolean validateParameter(){
    	// 1.开始header校验，对header信息进行校验
    	if(XSSSecurityConfig.IS_CHECK_HEADER){
    		if(!this.checkHeader()){
    			return false;
    		}
    	}
    		
    	// 2.开始parameter校验，对parameter信息进行校验
    	if(XSSSecurityConfig.IS_CHECK_PARAMETER){
	    	if(!this.checkParameters()){
	    		return false;
	    	}
    	}
    	return true;
    }
	
	/**
	 * 检查Header是否合法
	 * @return 合法返回true,否则返回false
	 */
	private boolean checkHeader(){
		Enumeration<String> headerParams = this.getHeaderNames();
		while(headerParams.hasMoreElements()){
			String headerName = headerParams.nextElement();
			String headerValue = this.getHeader(headerName);
			if(XSSSecurityManager.matches(headerValue)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 检查parameter是否合法
	 * @return 合法返回true,否则返回false
	 */
	private boolean checkParameters(){
		Map<String,String[]> submitParams = this.getParameterMap();
		for(Map.Entry<String, String[]> entry : submitParams.entrySet()){
			String[] submitValues = entry.getValue();
			for(String submitValue : submitValues){
				if(XSSSecurityManager.matches((String)submitValue)){
					return false;
				}
			}
		}
		return true;
	}

	
}
