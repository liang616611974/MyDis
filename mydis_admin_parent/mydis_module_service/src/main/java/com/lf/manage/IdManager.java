package com.lf.manage;

import com.lf.manager.id.IdGenerator;

/**
* <p>Title: IdManager.java<／p>
* <p>Description: id管理器,默认采用时间毫秒数生成算法<／p>
* @author Liangfeng
* @date 2017-3-6
* @version 1.0
 */
@SuppressWarnings("unchecked")
public class IdManager {

	//id生成算法器
	private IdGenerator idGenerator;
	
	
	public IdManager() {
		//默认的id生成算法，时间毫秒数
		this.idGenerator = new IdGenerator();
	}
	
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}
	
	/**
	 * 生成主键id,兼容多种类型的id类型值，比如int值、long值、String值
	 * @return
	 */
	public <T> T generateId(){
		//如果不设置算法生成器，则采用默认的生成算法，时间毫秒数算法
		return (T)idGenerator.generateId();
	}
	
	
	
}
