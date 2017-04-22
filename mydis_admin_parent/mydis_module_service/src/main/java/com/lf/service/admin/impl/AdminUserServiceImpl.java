/*
 * Powered By [liangfeng]
 * Web Site: http://www.liangfeng.com
 * 2017-03-06
 */

package com.lf.service.admin.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lf.exception.ServiceException;
import com.sdp.framework.base.BaseDao;
import com.sdp.framework.base.BaseServiceImpl;

import com.lf.model.admin.AdminUser;
import com.lf.vo.query.admin.AdminUserQuery;
import com.lf.dao.admin.AdminUserDao;
import com.lf.service.admin.AdminUserService;

@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser,AdminUserQuery,Long> implements AdminUserService{

	private static final Logger logger = LoggerFactory.getLogger(AdminUserService.class);
	
	@Autowired
	private AdminUserDao adminUserDao;
	
	@Override
	public BaseDao<AdminUser,AdminUserQuery,Long> getBaseDao() {
		return this.adminUserDao;
	}

	
}
