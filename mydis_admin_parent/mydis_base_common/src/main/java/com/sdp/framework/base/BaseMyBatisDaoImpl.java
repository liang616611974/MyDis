package com.sdp.framework.base;

import com.lf.exception.DaoException;
import com.sdp.framework.page.Page;
import com.sdp.framework.page.PageRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Title: BaseMyBatisDao.java
* @Description: mybatis实体dao实现类
* @author Liangfeng
* @date 2016-10-13
* @version 1.0
 */
public abstract class BaseMyBatisDaoImpl<E extends Serializable,Q extends PageRequest,PK extends Serializable> extends DaoSupport implements BaseDao<E,Q,PK> {
    private static final Logger logger = LoggerFactory.getLogger(BaseMyBatisDaoImpl.class);
    private static final String SQL_GET_SUFFIX = ".get";
    private static final String SQL_INSERT_SUFFIX = ".insert";
    private static final String SQL_UPDATE_SUFFIX = ".update";
    private static final String SQL_DELETE_SUFFIX = ".delete";
    private static final String SQL_QUERY_SUFFIX = ".query";
    private static final String SQL_QUERYBYVO_SUFFIX = ".queryByVO";
    private static final String SQL_COUNT_SUFFIX = ".count";
    private static final String PAGE_FILTER_OFFSET_KEY = "offset";
    private static final String PAGE_FILTER_PAGESIZE_KEY = "pageSize";
    private static final String PAGE_FILTER_LASTROWS_KEY = "lastRows";
    private static final String PAGE_FILTER_SORTCOLUMNS_KEY = "sortColumns";
    
    private SqlSessionFactory sqlSessionFactory;
    
    private SqlSessionTemplate sqlSessionTemplate;
    
    public abstract String getIbatisMapperNamesapce();

    @Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	@Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
    	return sqlSessionTemplate;
    }
	
	protected void checkDaoConfig() throws IllegalArgumentException {
		Assert.notNull(sqlSessionFactory,"sqlSessionFactory must be not null");
	}

	public int insert(E entity) {
		int affectCount = getSqlSessionTemplate().insert(getInsertStatement(), entity);
		return affectCount;
	}

	public int update(E entity) {
		int affectCount = getSqlSessionTemplate().update(getUpdateStatement(), entity);
		return affectCount;
	}

	public int delete(PK id) {
		int affectCount = getSqlSessionTemplate().delete(getDeleteStatement(), id);
		return affectCount;
	}
	
	public E get(PK id) {
        E e = getSqlSessionTemplate().selectOne(getFindByPrimaryKeyStatement(), id);
        return e;
    }

    public List<E> query(){
    	List<E> list = getSqlSessionTemplate().selectList(getFindAllStatement());
    	return list;
    }
    

    public List<E> query(Q query) {
    	List<E> list =  getSqlSessionTemplate().selectList(getFindAllByQueryStatement(),query);
    	return list;
    };
    

    public Page<E> queryPage(Q query) {
    	return pageQuery(getFindAllStatement(),query);
    	
    };
    

    public long count(Q query){
    	return sqlSessionTemplate.selectOne(getCountStatementForPaging(getFindAllStatement()),query);
    }
    
    /**
     * 分页查询
     * @param statementName 查询sql语句
     * @param pageRequest 分页请求对象
     * @return
     */
	protected  Page<E> pageQuery(String statementName, PageRequest pageRequest) {
		return pageQuery(getSqlSessionTemplate(),statementName,getCountStatementForPaging(statementName),pageRequest);
	}
	
	/**
	 * 分页查询
	 * @param sqlSessionTemplate sql模版
	 * @param statementName 查询sql语句名称
	 * @param countStatementName 统计sql语句名称
	 * @param pageRequest 分页请求对象
	 * @return
	 */
	private Page<E> pageQuery(SqlSessionTemplate sqlSessionTemplate,String statementName,String countStatementName, PageRequest pageRequest) {
		Page<E> page = null;
		try {
			//1.先统计记录
			Number totalCount = (Number) sqlSessionTemplate.selectOne(countStatementName,pageRequest);
			
			//2.如果统计记录为0，则不查实际数据
			if(totalCount == null || totalCount.longValue() <= 0) {
				page = new Page<E>(0,pageRequest.getPageNumber(),pageRequest.getPageSize(),pageRequest.getPageWidth());
				page.setResult(new ArrayList<E>(0));
				return page;
			}
			
			//3.如果统计记录大于0，则开始查实际数据
			page = new Page<E>(totalCount.longValue(),pageRequest.getPageNumber(),pageRequest.getPageSize(),pageRequest.getPageWidth());
			
			//3.1构建其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
			Map<String,Object> filters = new HashMap<String,Object>();
			filters.put(PAGE_FILTER_OFFSET_KEY, page.getFirstResultIndex());
			filters.put(PAGE_FILTER_PAGESIZE_KEY, page.getPageSize());
			filters.put(PAGE_FILTER_LASTROWS_KEY, page.getFirstResultIndex() + page.getPageSize());
			filters.put(PAGE_FILTER_SORTCOLUMNS_KEY, pageRequest.getSortColumns());
			
			//3.2将分页对象转成map对象,作为查询条件
			Map<String,Object> parameterObject = null;
			parameterObject = PropertyUtils.describe(pageRequest);
			filters.putAll(parameterObject);
			
			//3.3利用mybatis分页插件查询数据
			List<E> list = sqlSessionTemplate.selectList(statementName, filters, new RowBounds((int)page.getFirstResultIndex(),(int)page.getPageSize()));
			page.setResult(list);
		} catch (Exception e) {
			logger.error("BaseMyBatisDao分页查询 异常",e);
			throw new DaoException("BaseMyBatisDao分页查询 异常",e);
		}
		return page;
	}
	
	private String getFindByPrimaryKeyStatement() {
		return getIbatisMapperNamesapce() + SQL_GET_SUFFIX;
	}

	private String getInsertStatement() {
		return getIbatisMapperNamesapce() + SQL_INSERT_SUFFIX;
	}

	private String getUpdateStatement() {
		return getIbatisMapperNamesapce() + SQL_UPDATE_SUFFIX;
	}

	private String getDeleteStatement() {
		return getIbatisMapperNamesapce() + SQL_DELETE_SUFFIX;
	}

	private String getFindAllStatement() {
		return getIbatisMapperNamesapce() + SQL_QUERY_SUFFIX;
	}

	private String getFindAllByQueryStatement() {
		return getIbatisMapperNamesapce() + SQL_QUERYBYVO_SUFFIX;
	}

	private String getCountStatementForPaging(String statementName) {
		return statementName + SQL_COUNT_SUFFIX;
	}
	
}
