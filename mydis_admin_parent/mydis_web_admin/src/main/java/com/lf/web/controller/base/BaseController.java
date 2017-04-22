package com.lf.web.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lf.manage.IdManager;
import com.lf.service.admin.AdminUserService;

/**
* <p>Title: BaseController.java<／p>
* <p>Description: 公共控制类<／p>
* @author Liangfeng
* @date 2016-12-20
* @version 1.0
 */

@Controller
public class BaseController {
	
	/** 应用后台模块页面路径 */
	protected static final String HOME_ADMIN_JSP_PATH = "/home/admin";
	/** 应用业务模块页面路径 */
	protected static final String HOME_BUSINESS_JSP_PATH = "/home/business";
	/** 后台管理用户session_key */
	protected static final String ADMINUSER_SESSION_KEY = "user";
	/** 系统配置snowflakeid生成器开关 */
	protected static final String SYSTEM_SNOWFLAKEID_ENABLE = "snowflakeid_enable";
	
	@Autowired
	protected IdManager idManager;
	
	@Autowired
	protected AdminUserService adminUserService;

}
