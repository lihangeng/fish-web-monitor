package com.yihuacomputer.domain.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.Operator;
import com.yihuacomputer.common.OrderBy;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.PageResult;

/**
 * 数据库的基本操作，HIBERNATE实现
 *
 * @author xuxigang
 * @version
 * @since
 * @date 2010-8-3
 */
@SuppressWarnings("unchecked")
public class GenericHibernateDao extends HibernateDaoSupport implements IGenericDao {
	private final Logger logger = LoggerFactory.getLogger(GenericHibernateDao.class);

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 保存实体
	 */
	public <T> T save(T entity) {
		getHibernateTemplate().save(entity);
		logger.debug("save entity: {}", entity);
		return entity;
	}

	public <T> List<T> batchSave(List<T> entities){
		Session session = super.getSessionFactory().getCurrentSession();
		Transaction tx =session.beginTransaction();
		int count=0;
		for(T t:entities){
			session.save(t);
			if(count%100==0){   //每一千条刷新并写入数据库
                session.flush();
                session.clear();
            }
		}
		tx.commit();
		return entities;
	}

	/**
	 * 修改实体属性
	 */
	public <T> T update(T entity) {
		getHibernateTemplate().update(entity);
		logger.debug("update entity: {}", entity);
		return entity;
	}

	public <T> T merge(T entity) {
		getHibernateTemplate().merge(entity);
		logger.debug("merge entity: {}", entity);
		return entity;
	}

	/**
	 * 删除实体
	 */
	public <T> void delete(T entity) {
		getHibernateTemplate().delete(entity);
		logger.debug("delete entity: {}", entity);

	}

	public <T> void delete(Serializable id, Class<T> entityClass) {
		getHibernateTemplate().delete(load(id, entityClass));
	}

	/**
	 * 从缓冲中查找，先找ID，在从数据库中加载实体内容，找不到会抛出异常
	 */
	public <T> T load(Serializable id, Class<T> entityClass) {
		return getHibernateTemplate().load(entityClass, id);
	}

	/**
	 * 从数据库里查找，找不到返回null
	 */
	public <T> T get(Serializable id, Class<T> entityClass) {
		return getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 获取全部实体
	 */
	public <T> List<T> loadAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public <T> Criteria getCriteria(Class<T> entityClass) {
		String simpleName = entityClass.getSimpleName();
		String alias = simpleName.substring(0,1).toLowerCase() + simpleName.substring(1);
		return getSession().createCriteria(entityClass,alias).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	}

	/**
	 * 取得对象的主键名.
	 */
	public <T> String getIdName(Class<T> entityClass) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	private <T> Criteria addCriterionByFitler(IFilter filter,
			Class<T> entityClass) {
		List<String> alias = new ArrayList<String>();
		Criteria criteria = getCriteria(entityClass);
		if(filter == null){
			return criteria;
		}
		for (IFilterEntry c : filter.entrySet()) {
			if(c.getValue() == null) {
				continue;
			}
			String propertyName = c.getKey();
			if (propertyName.indexOf('.') > 0) {
				String[] ps = propertyName.split("\\.");
				if(!alias.contains(ps[0])){
					alias.add(ps[0]);
					criteria.createAlias(ps[0], ps[0],Criteria.LEFT_JOIN);
				}
			}
			if (Operator.EQ.equals(c.getOperator())) {
				criteria.add(Restrictions.eq(propertyName, c.getValue()));
			}else if(Operator.LIKE.equals(c.getOperator())){
				criteria.add(Restrictions.like(propertyName,(String)c.getValue(),MatchMode.ANYWHERE));
			}
			else if(Operator.NE.equals(c.getOperator())) {
				criteria.add(Restrictions.or(Restrictions.ne(propertyName, c.getValue()), Restrictions.isNull(propertyName)));
			}
			else if(Operator.LT.equals(c.getOperator())){
				criteria.add(Restrictions.lt(propertyName, c.getValue()));
			}
			else if(Operator.GT.equals(c.getOperator())){
				criteria.add(Restrictions.gt(propertyName, c.getValue()));
			}
			else if(Operator.GE.equals(c.getOperator())){
				criteria.add(Restrictions.ge(propertyName, c.getValue()));
			}
			else if(Operator.LE.equals(c.getOperator())){
				criteria.add(Restrictions.le(propertyName, c.getValue()));
			}else if(Operator.IN.equals(c.getOperator())){
				Collection<?> colection = (Collection<?>)c.getValue();
				criteria.add(Restrictions.in(propertyName, colection));
			}
		}
		return criteria;
	}

	//增加排序条件
	private <T> void addCriterionOrderByFitler(Criteria criteria,IFilter filter) {
	    if(filter == null){
           return;
        }
        for(OrderBy order : filter.getOrders()){
            criteria.addOrder(convert(order));
        }

	}
	private Order convert(OrderBy orderBy){
		if(orderBy.isAscending()){
			return Property.forName(orderBy.getPropertyName()).asc();
		}
		return Property.forName(orderBy.getPropertyName()).desc();
	}

 	public <T> List<T> findByEntity(T entity) {
		Criteria criteria = getCriteria(entity.getClass());
		criteria.add(Example.create(entity));
		return criteria.list();
	}

	public <T> List<T> findByFilter(IFilter filter, Class<T> entityClass) {
	    Criteria criteria =  addCriterionByFitler(filter, entityClass);
	    this.addCriterionOrderByFitler(criteria, filter);
	    return criteria.list();
	}

	@Override
	public <T> T findUniqueByFilter(IFilter filter, Class<T> entityClass) {
	    Criteria criteria =  addCriterionByFitler(filter, entityClass);
        this.addCriterionOrderByFitler(criteria, filter);
		return (T)criteria.uniqueResult();
	}

	@Override
	public <T> List<T> findByHQL(String hql, Object... values) {
		return super.getHibernateTemplate().find(hql, values);
	}

	@Override
	public <T> T findUniqueByHql(String hql, Object... values) {
		return (T)this.createQuery(hql, values).uniqueResult();
	}

	@Override
	public <T, IT> IPageResult<IT> page(int offset, int limit, IFilter filter,Class<T> entityClass) {
		PageResult<IT> result = new PageResult<IT>();
		Criteria criteria = addCriterionByFitler(filter, entityClass);
		Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		result.setTotal(total.intValue());
		criteria.setProjection(null);
        addCriterionOrderByFitler(criteria,filter);//增加排序条件
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc(this.getIdName(entityClass)))//默认根据主键升序排列
				.setFirstResult(offset)
				.setMaxResults(limit);
		List<IT> data = criteria.list();
		result.setData(data);
		return result;
	}

	@Override
	public IPageResult<? extends Object> page(int start, int limit, String hql, Object... values) {
		return page(start,limit,new Filter(),hql,values);
	}

	public IPageResult<? extends Object> page(int start, int limit , IFilter filter,String hql, Object... values) {
		PageResult<Object> page = new PageResult<Object>();
		Query query = this.createQueryByFilter(filter,hql, values);
		int total = this.countHqlResultByFilter(filter,hql, values);
		page.setTotal(total);

		query.setFirstResult(start);
		query.setMaxResults(limit);

		List<Object> data = query.list();
		page.setData(data);
		return page;
	}

	private int countHqlResultByFilter(IFilter filter, String hql, Object[] values) {
		String countHql = prepareCountHql(hql);
		try {
			Long count = findUniqueByHqlByFilter(filter,countHql,values);
			return count.intValue();
		} catch (Exception e) {
			throw new AppException("hql can't be auto count, hql is: " + countHql, e);
		}
	}

	private <T> T findUniqueByHqlByFilter(IFilter filter, String countHql, Object[] values) {
		return (T)this.createQueryByFilter(filter,countHql, values).uniqueResult();
	}

	public Query createQueryByFilter(IFilter filter,String outerHql, Object... values){
		FilterHql2 fh = new FilterHql2(filter);
		String hql = outerHql + fh.getHql();
		Query query = this.createQuery(hql, values);

		String [] names = fh.getParamNames();
		Object [] paramValues = fh.getValues();
		for (int i = 0; i< names.length; i ++) {
			addNamedParameter(query,names[i],paramValues[i]);
		}
		return query;
	}

	private void addNamedParameter(Query queryObject, String paramName, Object value) {
		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection<?>) value);
		}
		else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		}
		else {
			queryObject.setParameter(paramName, value);
		}
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 *
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected int countHqlResult(final String hql, final Object... values) {
		String countHql = prepareCountHql(hql);
		try {
			Long count = findUniqueByHql(countHql,values);
			return count.intValue();
		} catch (Exception e) {
			throw new AppException("hql can't be auto count, hql is: " + countHql, e);
		}
	}

	private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;
		//select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		return  "select count(*) " + fromHql;
	}

	private Query createQuery(final String hql, final Object... values) {
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	@Override
	public <T> T saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		logger.debug("update entity: {}", entity);
		return entity;
	}

	@Override
	public void batchUpdate(String hql, Object... values) {
		Query query = this.createQuery(hql, values);
		query.executeUpdate();
	}

    @Override
    public SQLQuery getSQLQuery(String sql) {
        return this.getSession().createSQLQuery(sql);
    }

    @Override
    public Session getHibernateSession() {
        return this.getSession();
    }

	@Override
	public IPageResult<Object> pageForSQL(int start, int limit,
			String sql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countSqlResultBySQL(String hql, Object[] values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPageResult<Object> pageForSQLTrans(int start, int limit,
			String sql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

}

class FilterHql{
	private String hql;
	private List<String> paramNames = new ArrayList<String>();
	private List<Object> values = new ArrayList<Object>();

	public FilterHql(IFilter outerFilter){
		IFilter filter = null;
	    if(outerFilter == null){
			filter = new Filter();
		}else{
		    filter = outerFilter;
		}
		StringBuffer filterHql = new StringBuffer(" ");
		for (IFilterEntry c : filter.entrySet()) {
			Object value = c.getValue();
			if(value == null) {
				continue;
			}
			String key = c.getKey();
			String name = key.replace(".", "_");
			if(paramNames.contains(name)){
				name = name + System.currentTimeMillis();
			}
			filterHql.append("	and ").append(c.getKey()).append(" ").append(c.getOperator().toString()).append(" :").append(name);
			paramNames.add(name);
			if(c.getOperator().equals(Operator.LIKE)){
				value =  "%" +  value + "%";
			}
			values.add(value);
		}
		this.hql = filterHql.toString();
	}

	public String getHql() {
		return hql;
	}


	public String[] getParamNames() {
		String [] result = new String [this.paramNames.size()] ;
		this.paramNames.toArray(result);
		return result;
	}


	public Object[] getValues() {
		return this.values.toArray();
	}


}

