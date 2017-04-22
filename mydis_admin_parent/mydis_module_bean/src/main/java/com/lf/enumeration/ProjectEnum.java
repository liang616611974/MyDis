package com.lf.enumeration;

/**
* <p>Title: ProjectEnum.java<／p>
* <p>Description: 关于项目对象的相关枚举常量<／p>
* @author Liangfeng
* @date 2017-1-10
* @version 1.0
 */
public class ProjectEnum {
	
	/**
	 * 项目状态
	 */
	public enum ProjectStatus{
		/** 1 预热中 */
		READY(1),
		
		/** 2 投标中 */
		BID(2),
		
		/** 3 投标结束 */
		JM_BEGIN(3),
		
		/** 4 已起息 */
		VALUE_BEGIN(4),
		
		/** 5 清算完成 */
		ORDER_RCV_SUCC(5),
		
		/** 6 已流标 */
		REFUND(6);
		
		private final int value;
		
		private ProjectStatus(int value) {
			this.value = value;
		}
		public int value() {
			return value;
		}
	}
	
	/**
	 * 项目归集状态
	 */
	public enum ProjectCollectState{
		/** 全部成功 */
		ALL_SUCC(1),
		
		/** 部分成功 */
		part_SUCC(2),
		
		/** 全部失败 */
		ALL_FAIL(3);
		
		private final int value;

		private ProjectCollectState(int value) {
			this.value = value;
		}
		public int value() {
			return value;
		}
	}

}
