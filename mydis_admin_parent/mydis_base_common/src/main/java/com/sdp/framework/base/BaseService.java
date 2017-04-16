package com.sdp.framework.base;

import java.io.Serializable;
import java.util.List;

import com.sdp.framework.page.Page;
import com.sdp.framework.page.PageRequest;

/**
* @Title: BaseService.java
* @Description: 业务基类接口 包含一些公共的业务操作
* @author Liangfeng
* @date 2016-11-10
* @version 1.0
 */
public interface BaseService <E extends Serializable,Q extends PageRequest,PK extends Serializable>{

	/**
	 * 保存实体
	 * @param entity
	 */
	void save(E entity);
	
	/**
	 * 修改实体
	 * @param entity
	 */
	void modify(E entity);
	
	/**
	 * 根据主键删除实体
	 * @param id 主键
	 */
	void remove(PK id);
	
	/**
	 * 根据主键获取实体
	 * @param id 主键
	 * @return
	 */
	E get(PK id);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	List<E> query();
	
	/**
	 * 根据查询对象条件查询所有结果
	 * @param query
	 * @return
	 */
	List<E> query(Q query);
	
	/**
	 * 根据查询对象条件分页查询结果
	 * @param query
	 * @return
	 */
	Page<E> queryPage(Q query);
	
	/**
	 * 根据查询对象条件统计
	 * @param query
	 * @return
	 */
	long count(Q query);
	
}
