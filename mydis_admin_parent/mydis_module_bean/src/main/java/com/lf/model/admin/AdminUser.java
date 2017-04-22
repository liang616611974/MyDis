/*
 * Powered By [liangfeng]
 * Web Site: http://www.liangfeng.com
 * 2017-03-06
 */

package com.lf.model.admin;

import java.util.Date;

import javax.validation.constraints.Max;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.lf.helper.DateHelper;
import com.lf.web.mvc.validator.common.annotation.CheckType;
import com.lf.web.mvc.validator.common.annotation.Validator;
import com.sdp.framework.base.BaseEntity;

/**
* @Title: AdminUser.java
* @Description:
* @author Liangfeng
* @date 2017-03-06
* @version 1.0
 */
public class AdminUser extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	
	private Long id; //column:id 主键id  
	
	@NotBlank 
    @Length(max=50)
	private String username; //column:username 用户名  
	
	@NotBlank 
    @Length(max=128)
	private String password; //column:password 密码  
	
	@Max(32767)
	private Integer status; //column:status 状态：0-禁用，1-正常  
	
	@Length(max=50)
    @Validator(checkType = CheckType.IDCARD, message="不是合法的身份证号码")
	private String idcard; //column:idcard 身分证号码  
	
	@Length(max=50)
    @Validator(checkType = CheckType.MOBILE, message="不是合法的手机号码")
	private String mobile; //column:mobile 手机号码  
	
	@Length(max=30)
    @Validator(checkType = CheckType.EMAIL, message="不是合法的电子邮箱")
	private String email; //column:email 电子邮箱  
	
	
	private Long createUser; //column:create_user 创建用户主键  
	
	
	private Date createTime; //column:create_time 创建时间  
	
	
	private Long modifyUser; //column:modify_user 修改用户主键  
	
	
	private Date modifyTime; //column:modify_time 修改时间  
	
	

	public AdminUser(){
	}

	public AdminUser(
		Long id
	){
		this.id = id;
	}

	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
	
		
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return this.username;
	}
	
		
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return this.password;
	}
	
		
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
		
	public void setIdcard(String value) {
		this.idcard = value;
	}
	
	public String getIdcard() {
		return this.idcard;
	}
	
		
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
		
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return this.email;
	}
	
		
	public void setCreateUser(Long value) {
		this.createUser = value;
	}
	
	public Long getCreateUser() {
		return this.createUser;
	}
	
		
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public String getCreateTimeStr() {
		return DateHelper.format(getCreateTime(), DATE_PATTERN);
	}
	
	public void setCreateTimeStr(String value) {
		setCreateTime(DateHelper.parse(value, DATE_PATTERN));
	}
		
	public void setModifyUser(Long value) {
		this.modifyUser = value;
	}
	
	public Long getModifyUser() {
		return this.modifyUser;
	}
	
		
	public void setModifyTime(Date value) {
		this.modifyTime = value;
	}
	
	public Date getModifyTime() {
		return this.modifyTime;
	}
	
	public String getModifyTimeStr() {
		return DateHelper.format(getModifyTime(), DATE_PATTERN);
	}
	
	public void setModifyTimeStr(String value) {
		setModifyTime(DateHelper.parse(value, DATE_PATTERN));
	}
		

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AdminUser == false) return false;
		if(this == obj) return true;
		AdminUser other = (AdminUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

