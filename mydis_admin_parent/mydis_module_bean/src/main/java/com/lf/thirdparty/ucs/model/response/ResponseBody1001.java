package com.lf.thirdparty.ucs.model.response;

import java.math.BigDecimal;
import java.util.List;

/**
* <p>Title: ResponseBody1001.java<／p>
* <p>Description: ucs接口1001响应体<／p>
* @author Liangfeng
* @date 2016-12-29
* @version 1.0
 */
public class ResponseBody1001 extends ResponseBody{
	
	private static final long serialVersionUID = 1L;

	private Integer totalCount; // 列表的总记录数

    private List<Order1001> dataList; // 查询结果
    
    public static class Order1001 {
    	
    	 private String orderNo; // 订单号

         private String prjCode; // 项目编号
         
         private BigDecimal orderAmount; // 订单支付总额
         
         private Integer orderParts; // 订单支付总份数

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getPrjCode() {
			return prjCode;
		}

		public void setPrjCode(String prjCode) {
			this.prjCode = prjCode;
		}

		public BigDecimal getOrderAmount() {
			return orderAmount;
		}

		public void setOrderAmount(BigDecimal orderAmount) {
			this.orderAmount = orderAmount;
		}

		public Integer getOrderParts() {
			return orderParts;
		}

		public void setOrderParts(Integer orderParts) {
			this.orderParts = orderParts;
		}
         
    }

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<Order1001> getDataList() {
		return dataList;
	}

	public void setDataList(List<Order1001> dataList) {
		this.dataList = dataList;
	}
       

}
