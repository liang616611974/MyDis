package com.lf.manager.id;

import com.lf.helper.UUIDHelper;

/**
* <p>Title: UUIDGenerator.java<／p>
* <p>Description: UUID生成算法<／p>
* @author Liangfeng
* @date 2017-3-6
* @version 1.0
 */
@SuppressWarnings("unchecked")
public class UUIDGenerator extends IdGenerator{

	@Override
	public <T> T generateId() {
		 return (T)UUIDHelper.generateCompressedUUID();
	}

}
