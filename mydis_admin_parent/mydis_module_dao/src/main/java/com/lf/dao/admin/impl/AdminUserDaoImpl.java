/*
 * Powered By [liangfeng]
 * Web Site: http://www.liangfeng.com
 * 2017-03-06
 */

package com.lf.dao.admin.impl;

import org.springframework.stereotype.Repository;
import com.lf.model.admin.AdminUser;
import com.lf.vo.query.admin.AdminUserQuery;

import com.lf.dao.admin.AdminUserDao;
import com.sdp.framework.base.BaseMyBatisDaoImpl;

@Repository
public class AdminUserDaoImpl extends BaseMyBatisDaoImpl<AdminUser,AdminUserQuery,Long> implements AdminUserDao{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "AdminUser";
	}
	

}
