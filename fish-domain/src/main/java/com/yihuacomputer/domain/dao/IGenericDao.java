
package com.yihuacomputer.domain.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 基础泛型方法DAO
 * @author xuxigang
 * @version 0.1
 * @since 0.1
 * @date 2010-8-3
 */
public interface IGenericDao {
	/**
	 * 增加实体
	 * @param entity
	 * @return
	 */
	 <T> T save(T entity);
	/**
	 * 修改实体
	 * @param entity
	 * @return
	 */
	 <T> T update(T entity);
	/**
	 * 批量更新（包括删除操作）
	 * @param hql
	 * @param values 
	 */
	 void batchUpdate(String hql, Object...values);
	/**
	 * 合并多个会话中的实体
	 * @param <T>
	 * @param entity
	 * @return
	 */
	 <T> T merge(T entity);
	/**
	 * 删除实体
	 * @param entity
	 */
	 <T> void delete(T entity) ;
	/**
	 * 根据主键删除实体
	 * @param id
	 */
	 <T> void delete(Serializable id,Class<T> entityClass);
	/**
	 * 根据ID加载实体，返回异常
	 * @param id
	 * @return T
	 */
	 <T> T load(Serializable id,Class<T> entityClass);
	/**
	 * 根据ID加载实体，返回null
	 * @param id
	 * @return T
	 */
	 <T> T get(Serializable id,Class<T> entityClass);
	
	/**
	 * 加载所有的对象
	 * @return List<T>
	 */
	 <T> List<T> loadAll(Class<T> entityClass);
	/**
	 * 根据实体查找
	 * @param <T>
	 * @param entity
	 * @return
	 */
	 <T> List<T> findByEntity(T entity);
	/**
	 * 根据过滤器查询
	 * @param <T>
	 * @param fitler
	 * @return
	 */
	 <T> List<T> findByFilter(final IFilter filter,Class<T> entityClass);
	/**
	 * 根据过滤器查找唯一
	 * @param <T>
	 * @param filter
	 * @return
	 */
	 <T> T findUniqueByFilter(final IFilter filter,Class<T> entityClass);
	/**
	 * 获得Criteria查询
	 * @return
	 */
	 <T> Criteria getCriteria(Class<T> entityClass);
	/**
	 * 分页查找,条件是自定义的IFilter
	 * @param <T>
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	 <T,IT> IPageResult<IT> page(int offset,int limit,IFilter filter,Class<T> entityClass);

	/**
	 * HQL的方式查询集合
	 * @param <T>
	 * @param hql
	 * @param values
	 * @return
	 */
	 <T> List<T> findByHQL(String hql,Object...values);
	/**
	 * HQL的方式查询单条记录
	 * @param <T>
	 * @param hql
	 * @param values
	 * @return
	 */
	 <T> T findUniqueByHql(String hql,Object...values);
	
	//TODO 缺少查找某列值的接口

	/**
	 * 根据hql语句分页查找
	 * 查找出来的可能是一个实体，一些属性列，或者几个实体的组合
	 * @param start
	 * @param limit
	 * @param hql hql查询语句
	 * @param values
	 * @return
	 */
	 IPageResult<? extends Object> page(int start,int limit,String hql,Object...values);
	
	/**
	 * 
	 * @param start
	 * @param limit
	 * @param filter 过滤条件
	 * @param hql
	 * @param values
	 * @return
	 */
	 IPageResult<? extends Object> page(int start, int limit , IFilter filter,String hql, Object... values);
	
	 <T> T saveOrUpdate(T entity);
	 
	 /**
	  * 支持native sql 的查询
	  * @param sql 标准的sql语句
	  * @return
	  */
	 SQLQuery getSQLQuery(String sql);
	 /**
	  * 获得会话
	  * @return
	  */
	 Session getHibernateSession();
	 
	
}
