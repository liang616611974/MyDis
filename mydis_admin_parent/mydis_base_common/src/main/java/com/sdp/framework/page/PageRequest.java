package com.sdp.framework.page;

import java.io.Serializable;

/**
* @Title: PageRequest.java
* @Description: 分页请求类
* @author Liangfeng
* @date 2016-11-10
* @version 1.0
 */
public class PageRequest implements Serializable {

	
	private static final long serialVersionUID = 1634988769228106224L;
	
	/**
	 * 页号码,页码从1开始
	 */
	private long pageNumber;
	
	/**
	 * 分页大小
	 */
	private long pageSize;
	
	/**
	 * 页码范围大小
	 */
	private long pageWidth;
	
	/**
	 * 排序的多个列,如: username desc
	 */
	private String sortColumns;
	
	public PageRequest() {
		
	}
	
	public long getPageWidth() {
		return pageWidth;
	}

	public void setPageWidth(long pageWidth) {
		this.pageWidth = pageWidth;
	}
	
	public long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}
	
	
}
