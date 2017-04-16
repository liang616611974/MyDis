package com.sdp.framework.base;

import com.sdp.framework.page.PageRequest;

public class BaseQuery extends PageRequest implements java.io.Serializable {
	private static final long serialVersionUID = -360860474471966681L;
	protected static final long DEFAULT_PAGE_SIZE = 10L;
	protected static final long DEFAULT_PAGE_WIDTH = 10L;
	protected static final String DATE_PATTERN = "yyyy-MM-dd";
	protected static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	protected static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.S";
	
	static {
		System.out.println("BaseQuery.DEFAULT_PAGE_SIZE=" + DEFAULT_PAGE_SIZE);
		System.out.println("BaseQuery.DEFAULT_PAGE_WIDTH=" + DEFAULT_PAGE_WIDTH);
	}
    
	public BaseQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
		setPageWidth(DEFAULT_PAGE_WIDTH);
	}
	  
}
