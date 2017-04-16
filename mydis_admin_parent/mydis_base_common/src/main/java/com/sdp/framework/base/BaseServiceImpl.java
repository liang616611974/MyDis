package com.sdp.framework.base;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lf.exception.ServiceException;
import com.sdp.framework.page.Page;
import com.sdp.framework.page.PageRequest;

/**
 * @Title: BaseService.java
 * @Description: 业务基类，实现增删查改的一些操作
 * @author Liangfeng
 * @date 2016-10-24
 * @version 1.0
 */
@SuppressWarnings(value = { "rawtypes", "unchecked" })
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public abstract class BaseServiceImpl<E extends Serializable,Q extends PageRequest,PK extends Serializable> implements BaseService<E,Q,PK> {

	private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

	protected abstract BaseDao getBaseDao();

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void save(E entity) {
		try {
			getBaseDao().insert(entity);
		} catch (Exception e) {
			logger.error("保存实体发生异常", e);
			throw new ServiceException("保存实体发生异常",e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void modify(E entity) {
		try {
			getBaseDao().update(entity);
		} catch (Exception e) {
			logger.error("修改实体发生异常", e);
			throw new ServiceException("修改实体发生异常",e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void remove(PK id) {
		try {
			getBaseDao().delete(id);
		} catch (Exception e) {
			logger.error("删除实体发生异常", e);
			throw new ServiceException("删除实体发生异常",e);
		}
	}
	
	@Override
	public E get(PK id) {
		try {
			return (E) getBaseDao().get(id);
		} catch (Exception e) {
			logger.error("获取实体发生异常", e);
			throw new ServiceException("获取实体发生异常",e);
		}
	}

	@Override
	public List<E> query() {
		try {
			return getBaseDao().query();
		} catch (Exception e) {
			logger.error("查询所有实体发生异常", e);
			throw new ServiceException("查询所有实体发生异常",e);
		}
	}
	
	@Override
	public List<E> query(Q query){
		try{
			return getBaseDao().query(query);
		}catch (Exception e) {
			logger.error("根据查询对象条件查询发生异常",e);
			throw new ServiceException("根据查询对象条件查询发生异常",e);
		}
	}
	
	@Override
	public Page<E> queryPage(Q query) {
		try{
			return getBaseDao().queryPage(query);
		}catch (Exception e) {
			logger.error("根据查询对象条件分页查询发生异常",e);
			throw new ServiceException("根据查询对象条件分页查询发生异常",e);
		}
	}
	
	@Override
	public long count(Q query){
		try{
			return getBaseDao().count(query);
		}catch (Exception e) {
			logger.error("根据查询对象条件统计发生异常",e);
			throw new ServiceException("根据查询对象条件统计发生异常",e);
		}
	}
	
	
	
	
	
	
	

	
}
