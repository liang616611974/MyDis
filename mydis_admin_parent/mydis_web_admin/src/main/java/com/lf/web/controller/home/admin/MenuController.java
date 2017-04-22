package com.lf.web.controller.home.admin;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lf.common.Result;

/**
* @Title: MenuController.java
* @Description: 系统菜单控制器
* @author Liangfeng
* @date 2016-9-12
* @version 1.0
 */
@Controller
public class MenuController{
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@RequestMapping(value="/menu/getMenuForMain")
	public @ResponseBody Result<String> getMenuForMain(HttpServletResponse response){
		//1.结果定义
		Result<String> result = null;
		try{
			//2.结果初始化
			result = new Result<String>();
			//3.开始处理业务
			StringBuffer sb = new StringBuffer();
			sb.append("[{" + "name:\"父节点一\"," + "mId:1,pId:0,open:true,children: [");
			sb.append("{ name:\"后台用户管理\",mId:2,pId:1,mUrl:\"/adminUser/list.do\"}");
			sb.append("]");
			sb.append("}]");
			
			//4.返回结果
			result.setData(sb.toString());
			result.setFlag(true);
			result.setCode("0000");
			result.setMsg("获取主面板菜单 成功");
			logger.info("data is {}",sb.toString() );
			//throw new RuntimeException();
		}catch (Exception e) {
			//异常处理
			result.setData(null);
			result.setFlag(false);
			result.setCode("9999");
			result.setMsg("获取主面板菜单 失败");
			//打印日志
			logger.error("获取主面板菜单异常",e);
		}
		return result;
	}
	
}
