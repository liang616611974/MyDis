package com.lf.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lf.helper.SystemConfigHelper;

/**
* <p>Title: InitResourceListener.java<／p>
* <p>Description: 初始化资源监听器<／p>
* @author Liangfeng
* @date 2017-2-10
* @version 1.0
 */
public class ResourceInitListener implements ServletContextListener{

	/**
	 * 监听器销毁
	 */
	public void contextDestroyed(ServletContextEvent event) {}

	/**
	 * 监听器初始化
	 */
	public void contextInitialized(ServletContextEvent event) {
		// 1.加载系统配置文件
		String systemConfigPath = event.getServletContext().getInitParameter("systemConfigPath"); 
		SystemConfigHelper.init(systemConfigPath);
	}

	
	
}
