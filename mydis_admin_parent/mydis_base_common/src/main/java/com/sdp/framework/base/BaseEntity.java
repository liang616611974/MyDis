package com.sdp.framework.base;

/**
* @Title: BaseEntity.java
* @Description: 实体基类，包含一些共有属性
* @author Liangfeng
* @date 2016-10-13
* @version 1.0
 */
public class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7200095849148417467L;

	protected static final String DATE_PATTERN = "yyyy-MM-dd";
		
	protected static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	protected static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.S";
	
}
