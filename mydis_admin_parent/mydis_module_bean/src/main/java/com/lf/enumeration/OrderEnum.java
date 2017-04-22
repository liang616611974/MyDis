package com.lf.enumeration;

/**
* <p>Title: OrderEnum.java<／p>
* <p>Description: 关于订单对象的相关枚举常量<／p>
* @author Liangfeng
* @date 2017-1-10
* @version 1.0
 */
public class OrderEnum {
	
	/**
	 * 项目状态
	 */
	public enum OrderStatus{
		/** 1 支付中*/
		PAY_PROCESS(1),
		
		/** 2 支付成功 */
		PAY_SUCC(2),
		
		/** 3 支付失败*/
		PAY_FAIL(3);
		
		private final int value;
		
		private OrderStatus(int value) {
			this.value = value;
		}
		
		public int value() {
			return value;
		}
	}

}
