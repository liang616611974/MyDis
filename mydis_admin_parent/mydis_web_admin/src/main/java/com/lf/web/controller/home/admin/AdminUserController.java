/*
 * Powered By [liangfeng]
 * Web Site: http://www.liangfeng.com
 * 2017-03-07
 */


package com.lf.web.controller.home.admin;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lf.common.Result;
import com.lf.model.admin.AdminUser;
import com.lf.vo.query.admin.AdminUserQuery;
import com.lf.web.controller.base.BaseController;
import com.lf.web.mvc.interceptor.token.annotation.Token;
import com.sdp.framework.page.Page;

@Controller
public class AdminUserController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class );
	
	/** 列表页 */
	@Token(save=true)
	@RequestMapping(value="/adminUser/list.do")
	public String list(ModelMap model) {
		return  HOME_ADMIN_JSP_PATH + "/adminUser/adminUser_list";
	}
	
	/** 列表页数据 */
	@RequestMapping(value="/adminUser/listData.do")
	@ResponseBody
	public Map<String,Object> listData(AdminUserQuery query,int page,int rows) {
		//1.初始化
		if(query==null){
			query = new AdminUserQuery();
		}
		//2.分页查询
		//设置参数
		query.setPageNumber(page);
		query.setPageSize(rows);
		query.setSortColumns("create_time desc");
		Map<String,Object> strObjs = new HashMap<String,Object>();
		try{
			Page<AdminUser> adminUserPage = adminUserService.queryPage(query);
			strObjs.put("total", adminUserPage.getAllCount());
			strObjs.put("rows", adminUserPage.getResult());
		}catch (Exception e) {
			logger.error("分页查询AdminUser列表数据发生异常 查询参数query:{}",query,e);
		}
		//3.返回结果
		return strObjs;
	}
	
	/** 显示 */
	@RequestMapping(value="/adminUser/detail.do")
	public String detail(ModelMap model,Long id){
		AdminUser adminUser = adminUserService.get(id);
		model.addAttribute("adminUser",adminUser);
		return HOME_ADMIN_JSP_PATH + "/adminUser/adminUser_detail";
	}
	
	/**新增*/
	@Token(valid=true)
	@RequestMapping(value="/adminUser/save.do",method=RequestMethod.POST)
	@ResponseBody
	public Result<String> save(@Validated AdminUser adminUser,BindingResult errors,HttpServletRequest request){
		// 1.初始化
		Result<String> result = new Result<String>();
		// 2.校验参数
		if (errors.hasErrors()) {
			result.setCode("9998");
			result.setMsg("提交参数错误");
			return result;
		}
		// 3.保存数据
		long id = idManager.generateId();
		adminUser.setId(id);
		adminUser.setCreateTime(new Date());
		AdminUser user = (AdminUser) request.getSession().getAttribute(ADMINUSER_SESSION_KEY);
		if (user != null) {
			adminUser.setCreateUser(user.getId());
		}
		try{
			adminUserService.save(adminUser);
			result.setFlag(true);
		}catch (Exception e) {
			result.setCode("9999");
			result.setMsg("保存后台用户 异常");
			logger.error("保存后台用户 异常 adminUser:{}",adminUser,e);
		}
		//4.返回结果
		return result;
	}
	
	/**编辑 */
	@RequestMapping(value="/adminUser/edit.do")
	@ResponseBody
	public Result<AdminUser> edit(Long id){
		// 1.初始化
		Result<AdminUser> result = new Result<AdminUser>();
		// 2.校验参数
		if (id==null) {
			result.setCode("9998");
			result.setMsg("提交参数错误");
			return result;
		}
		try{
			//3.查询数据
			AdminUser adminUser = adminUserService.get(id);
			result.setData(adminUser);
			result.setFlag(true);
		}catch (Exception e) {
			result.setCode("9999");
			result.setMsg("获取后台用户编辑信息 异常" );
			logger.error("获取后台用户编辑信息 异常 id:{}",id,e);
		}
		//4.返回结果
		return result;
	}
	
	/**修改*/
	@RequestMapping(value="/adminUser/modify.do", method=RequestMethod.POST)
	@ResponseBody
	public Result<String> modify(@Validated AdminUser adminUser,BindingResult errors,HttpServletRequest request){
		// 1.初始化
		Result<String> result = new Result<String>();
		// 2.校验参数
		if (errors.hasErrors()) {
			result.setCode("9998");
			result.setMsg("参数错误");
			return result;
		}
		// 3.保存数据
		adminUser.setModifyTime(new Date());
		AdminUser user = (AdminUser) request.getSession().getAttribute(ADMINUSER_SESSION_KEY);
		if (user != null) {
			adminUser.setModifyUser(user.getId());
		}
		adminUser.setModifyTime(new Date());
		try{
			adminUserService.modify(adminUser);
			result.setFlag(true);
		}catch (Exception e) {
			result.setCode("9999");
			result.setMsg("修改后台用户 异常" );
			logger.error("修改后台用户 异常 adminUser:{}",adminUser,e);
		}
		//4.返回结果
		return result;
	}
	
	/** 删除 */
	@RequestMapping(value="/adminUser/remove")
	public @ResponseBody Result<String> remove(Long id) {
		//1.初始化
		Result<String> result = new Result<String>();
		try{
			adminUserService.remove(id);
			result.setFlag(true);
		}catch (Exception e) {
			result.setCode("9999");
			result.setMsg("删除后台用户 异常" );
			logger.error("删除后台用户 异常 id:{}",id,e);
		}
		//4.返回结果
		return result;
	}
	
	
	
	
}

