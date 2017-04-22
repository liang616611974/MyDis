/*
 * Powered By [liangfeng]
 * Web Site: http://www.liangfeng.com
 * 2017-03-06
 */

package com.lf.vo.query.admin;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.lf.helper.DateHelper;
import com.sdp.framework.base.BaseQuery;


/**
* @Title: AdminUser.java
* @Description:
* @author Liangfeng
* @date 2017-03-06
* @version 1.0
 */
public class AdminUserQuery extends BaseQuery {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 主键id */
	private Long id;
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
	/** 状态：0-禁用，1-正常 */
	private Integer status;
	/** 身分证号码 */
	private String idcard;
	/** 手机号码 */
	private String mobile;
	/** 电子邮箱 */
	private String email;
	/** 创建用户主键 */
	private Long createUser;
	/** 创建时间 */
	private Date createTimeBegin;
	private Date createTimeEnd;
	/** 修改用户主键 */
	private Long modifyUser;
	/** 修改时间 */
	private Date modifyTimeBegin;
	private Date modifyTimeEnd;

	public Long getId() {
		return this.id;
	}
	
	public void setId(Long value) {
		this.id = value;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public String getIdcard() {
		return this.idcard;
	}
	
	public void setIdcard(String value) {
		this.idcard = value;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public Long getCreateUser() {
		return this.createUser;
	}
	
	public void setCreateUser(Long value) {
		this.createUser = value;
	}
	
	public Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	
	public void setCreateTimeBegin(Date value) {
		this.createTimeBegin = value;
	}
	
	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date value) {
		this.createTimeEnd = value;
	}
	
	public String getCreateTimeBeginStr() {
		return DateHelper.format(getCreateTimeBegin(), DATETIME_PATTERN);
	}
	
	public void setCreateTimeBeginStr(String value) {
		setCreateTimeBegin(DateHelper.parse(value, DATETIME_PATTERN));
	}
	
	public String getCreateTimeEndStr() {
		return DateHelper.format(getCreateTimeEnd(), DATETIME_PATTERN);
	}
	
	public void setCreateTimeEndStr(String value) {
		setCreateTimeEnd(DateHelper.parse(value, DATETIME_PATTERN));
	}
	
	public Long getModifyUser() {
		return this.modifyUser;
	}
	
	public void setModifyUser(Long value) {
		this.modifyUser = value;
	}
	
	public Date getModifyTimeBegin() {
		return this.modifyTimeBegin;
	}
	
	public void setModifyTimeBegin(Date value) {
		this.modifyTimeBegin = value;
	}
	
	public Date getModifyTimeEnd() {
		return this.modifyTimeEnd;
	}
	
	public void setModifyTimeEnd(Date value) {
		this.modifyTimeEnd = value;
	}
	
	public String getModifyTimeBeginStr() {
		return DateHelper.format(getModifyTimeBegin(), DATETIME_PATTERN);
	}
	
	public void setModifyTimeBeginStr(String value) {
		setModifyTimeBegin(DateHelper.parse(value, DATETIME_PATTERN));
	}
	
	public String getModifyTimeEndStr() {
		return DateHelper.format(getModifyTimeEnd(), DATETIME_PATTERN);
	}
	
	public void setModifyTimeEndStr(String value) {
		setModifyTimeEnd(DateHelper.parse(value, DATETIME_PATTERN));
	}
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

