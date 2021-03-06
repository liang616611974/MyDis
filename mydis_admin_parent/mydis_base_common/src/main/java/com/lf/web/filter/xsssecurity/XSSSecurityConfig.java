package com.lf.web.filter.xsssecurity;

/**
 * 
* <p>Title: XSSSecurityConfig.java<／p>
* <p>Description: XSSSecurityConfig配置<／p>
* @author Liangfeng
* @date 2017-2-21
* @version 1.0
 */
public class XSSSecurityConfig {
	
	/**
	 * CHECK_HEADER：是否开启header校验
	 */
	public static boolean IS_CHECK_HEADER; 
	
	/**
	 * CHECK_PARAMETER：是否开启parameter校验
	 */
	public static boolean IS_CHECK_PARAMETER;
	
	/**
	 * IS_LOG：是否记录日志
	 */
	public static boolean IS_LOG;
	
	/**
	 * IS_LOG：是否中断操作
	 */
	public static boolean IS_CHAIN;
	
	/**
	 * REPLACE：是否开启替换
	 */
	public static boolean IS_REPLACE;
	
	
	
}
