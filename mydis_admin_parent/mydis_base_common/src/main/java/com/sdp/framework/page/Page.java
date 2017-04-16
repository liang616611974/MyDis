package com.sdp.framework.page;

import java.util.ArrayList;
import java.util.List;

/**
* @Title: Page.java
* @Description: 分页类
* @author Liangfeng
* @date 2016-11-10
* @version 1.0
 */
public class Page<T> {

	// 元素结果集
	private List<T> result = null;

	// 元素集合中第一条记录在总记录数中的索引
	private long firstResultIndex = 0;

	// 每页的记录数
	private long pageSize = 10;

	// 页码的最大范围
	private long pageWidth = 10;

	// 总记录数
	private long allCount = 0;

	// 总页数
	private long allPage = 0;

	// 当前页
	private long currPageNumber = 0;

	// 页码范围的开始
	private long start = 0;

	// 页码范围的结束
	private long end = 0;

	public Page(long allCount, long currPageNumber, long pageSize, long pageWidth) {
		this.pageSize = pageSize;
		this.allCount = allCount;
		this.currPageNumber = currPageNumber;
		this.pageWidth = pageWidth;

		// 根据总记录数算出总页数
		if (this.allCount % this.pageSize == 0)
			this.allPage = (this.allCount / this.pageSize);
		else {
			this.allPage = (this.allCount / this.pageSize + 1);
		}

		// 首记录索引
		this.firstResultIndex = ((this.currPageNumber - 1) * this.pageSize);

		// 如果总页数超过页数范围
		if (this.allPage > this.pageWidth) {
			long back = this.pageWidth / 2;
			long forward = this.pageWidth / 2;

			if (this.pageWidth % 2 == 0) {
				back--;
			}

			// 起始页为当前页 - back
			this.start = (this.currPageNumber - back);
			// 结束页为当前页 + forward
			this.end = (this.currPageNumber + forward);

			// 如果起始页小于1，则为1
			if (this.start < 1) {
				this.start = 1;
				this.end = this.pageWidth;
			}

			// 如果结束页大于总页数，则为总页数
			if (this.end > this.allPage) {
				this.end = this.allPage;
				this.start = (this.end - 9);
			}
		}// 否则从1开始到总页数
		else {
			this.start = 1;
			this.end = this.allPage;
		}
		// 如果当前页大于总页数则为总页数
		if (this.currPageNumber > this.allPage)
			this.currPageNumber = this.allPage;
		// 如果当前页小于1则为1
		if (this.currPageNumber < 1) {
			this.currPageNumber = 1;
		}
	}

	/**
	 * 是否是首页（第一页），第一页页码为1
	 * 
	 * @return 首页标识
	 */
	public boolean isFirstPage() {
		return getCurrPageNumber() == 1;
	}

	/**
	 * 是否是最后一页
	 * 
	 * @return 末页标识
	 */
	public boolean isLastPage() {
		return getCurrPageNumber() >= getAllPage();
	}

	/**
	 * 是否有下一页
	 * 
	 * @return 下一页标识
	 */
	public boolean isHasNextPage() {
		return getAllPage() > getCurrPageNumber();
	}

	/**
	 * 是否有上一页
	 * 
	 * @return 上一页标识
	 */
	public boolean isHasPreviousPage() {
		return getCurrPageNumber() > 1;
	}

	/**
	 * 获取元素结果集
	 * 
	 * @return
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置元素集合中第一条记录在总记录数中的索引
	 * @param result
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * 获取元素集合中第一条记录在总记录数中的索引
	 * 
	 * @return
	 */
	public long getFirstResultIndex() {
		return firstResultIndex;
	}

	/**
	 * 获取下一页编码
	 * 
	 * @return 下一页编码
	 */
	public long getNextPageNumber() {
		return getCurrPageNumber() + 1;
	}

	/**
	 * 获取上一页编码
	 * 
	 * @return 上一页编码
	 */
	public long getPreviousPageNumber() {
		return getCurrPageNumber() - 1;
	}

	/**
	 * 得到用于多页跳转的页码
	 * 
	 * @return
	 */
	public List<Long> getLinkPageNumbers() {
		List<Long> list = new ArrayList<Long>();
		for(long i=getStart(); i<=getEnd(); i++){
			list.add(i);
		}
		return list;
	}

	/**
	 * 设置第一结果
	 * @param firstResultIndex
	 */
	public void setFirstResultIndex(long firstResultIndex) {
		this.firstResultIndex = firstResultIndex;
	}

	/**
	 * 获取每页的记录数
	 * @return
	 */
	public long getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数
	 * @param pageSize
	 */
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取每页页码的范围宽度
	 * @return
	 */
	public long getPageWidth() {
		return pageWidth;
	}

	/**
	 * 设置每页页码的范围宽度
	 * @param pageWidth
	 */
	public void setPageWidth(long pageWidth) {
		this.pageWidth = pageWidth;
	}

	/**
	 * 获取总记录数
	 * @return
	 */
	public long getAllCount() {
		return allCount;
	}

	/**
	 * 设置总记录数
	 * @param allCount
	 */
	public void setAllCount(long allCount) {
		this.allCount = allCount;
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public long getAllPage() {
		return allPage;
	}

	/**
	 * 设置总页数
	 * @param allPage
	 */
	public void setAllPage(long allPage) {
		this.allPage = allPage;
	}

	/**
	 * 获取当前页页码
	 * @return
	 */
	public long getCurrPageNumber() {
		return currPageNumber;
	}

	/**
	 * 设置当前页码
	 * @param currPageNumber
	 */
	public void setCurrPageNumber(long currPageNumber) {
		this.currPageNumber = currPageNumber;
	}

	/**
	 * 获取开始页码
	 * @return
	 */
	public long getStart() {
		return start;
	}

	/**
	 * 设置开始页码
	 * @param start
	 */
	public void setStart(long start) {
		this.start = start;
	}

	/**
	 * 获取最后页码
	 * @return
	 */
	public long getEnd() {
		return end;
	}

	/**
	 * 设置最后页码
	 * @param end
	 */
	public void setEnd(long end) {
		this.end = end;
	}

}