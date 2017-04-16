package com.lf.manager.id;

/**
* <p>Title: IdGenerator.java<／p>
* <p>Description: id生成器基类<／p>
* @author Liangfeng
* @date 2017-3-6
* @version 1.0
 */
@SuppressWarnings("unchecked")
public class IdGenerator {
	
	/**
	 * 生成主键id
	 * @return
	 */
	public <T> T generateId(){
		return (T)((Long)System.currentTimeMillis());
	}

}
