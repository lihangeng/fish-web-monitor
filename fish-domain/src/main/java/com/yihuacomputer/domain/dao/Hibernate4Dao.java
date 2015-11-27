/**
 *
 */
package com.yihuacomputer.domain.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.Operator;
import com.yihuacomputer.common.OrderBy;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.PageResult;

/**
 * @author xuxigang
 *
 */
@SuppressWarnings("unchecked")
public class Hibernate4Dao implements IGenericDao {

	private final Logger logger = LoggerFactory.getLogger(Hibernate4Dao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public <T> T save(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	public <T> List<T> loadAll(Class<T> entityClass) {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(entityClass).list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public <T> T update(T entity) {
		Session session = this.getHibernateSession();
		session.update(entity);
		return entity;
	}

	@Override
	public void batchUpdate(String hql, Object... values) {
		Query query = this.createQuery(hql, values);
		int rows = query.executeUpdate();
		logger.info(String.format("batchUpdate:[%s] rows was effected!", rows));
	}

	@Override
	public <T> T merge(T entity) {
		Session session = this.getHibernateSession();
		session.merge(entity);
		return entity;
	}

	@Override
	public <T> void delete(T entity) {
		Session session = this.getHibernateSession();
		session.delete(entity);
	}

	@Override
	public <T> void delete(Serializable id, Class<T> entityClass) {
		delete(load(id, entityClass));
	}

	public <T> T load(Serializable id, Class<T> entityClass) {
		Session session = this.getHibernateSession();
		T entity = (T) session.load(entityClass, id);
		return entity;
	}

	public <T> T get(Serializable id, Class<T> entityClass) {
		Session session = this.getHibernateSession();
		T entity = (T) session.get(entityClass, id);
		return entity;
	}

	public <T> List<T> findByEntity(T entity) {
		Criteria criteria = getCriteria(entity.getClass());
		criteria.add(Example.create(entity));
		return criteria.list();
	}

	@Override
	public <T> List<T> findByFilter(IFilter filter, Class<T> entityClass) {
		Criteria criteria = addCriterionByFitler(filter, entityClass);
		this.addCriterionOrderByFitler(criteria, filter);
		return criteria.list();
	}

	@Override
	public <T> T findUniqueByFilter(IFilter filter, Class<T> entityClass) {
		Criteria criteria = addCriterionByFitler(filter, entityClass);
		this.addCriterionOrderByFitler(criteria, filter);
		return (T) criteria.uniqueResult();
	}

	@Override
	public <T> Criteria getCriteria(Class<T> entityClass) {
		String simpleName = entityClass.getSimpleName();
		String alias = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
		return this.getHibernateSession().createCriteria(entityClass, alias)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	}

	@Override
	public <T, IT> IPageResult<IT> page(int offset, int limit, IFilter filter, Class<T> entityClass) {
		PageResult<IT> result = new PageResult<IT>();
		Criteria criteria = addCriterionByFitler(filter, entityClass);
		Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		result.setTotal(total.intValue());
		criteria.setProjection(null);
		addCriterionOrderByFitler(criteria, filter);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.desc(this.getIdName(entityClass))).setFirstResult(offset).setMaxResults(limit);
		List<IT> data = criteria.list();
		result.setData(data);
		return result;
	}

	public <T> List<T> findByHQL(String hql, Object... values) {
		Query query = this.createQuery(hql, values);
		return query.list();
	}

	public <T> T findUniqueByHql(String hql, Object... values) {
		Query query = this.createQuery(hql, values);
		return (T) query.uniqueResult();
	}

	@Override
	public IPageResult<? extends Object> page(int start, int limit, String hql, Object... values) {
		return page(start, limit, new Filter(), hql, values);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public IPageResult<? extends Object> page(int start, int limit, IFilter filter, String hql, Object... values) {
		PageResult<Object> page = new PageResult<Object>();
		int total = this.countHqlResultByFilter(filter, hql, values);
		Query query = this.createQueryByFilter(filter, hql, values);
		page.setTotal(total);

		query.setFirstResult(start);
		query.setMaxResults(limit);

		List data = query.list();
		page.setData(data);
		return page;
	}

	@Override
	public <T> T saveOrUpdate(T entity) {
		Session session = this.getHibernateSession();
		session.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public SQLQuery getSQLQuery(String sql) {
		return this.getHibernateSession().createSQLQuery(sql);
	}

	@Override
	public Session getHibernateSession() {
		Session session = this.getSessionFactory().getCurrentSession();
		return session;
	}

	private Query createQuery(final String hql, final Object... values) {
		Query query = this.getHibernateSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	private <T> Criteria addCriterionByFitler(IFilter filter, Class<T> entityClass) {
		List<String> alias = new ArrayList<String>();
		Criteria criteria = getCriteria(entityClass);
		if (filter == null) {
			return criteria;
		}
		for (IFilterEntry c : filter.entrySet()) {
			if (c.getValue() == null) {
				continue;
			}
			String propertyName = c.getKey();
			if (propertyName.indexOf('.') > 0) {
				String[] ps = propertyName.split("\\.");
				if (!alias.contains(ps[0])) {
					alias.add(ps[0]);
					criteria.createAlias(ps[0], ps[0], JoinType.LEFT_OUTER_JOIN);
				}
			}
			if (Operator.EQ.equals(c.getOperator())) {
				criteria.add(Restrictions.eq(propertyName, c.getValue()));
			} else if (Operator.LIKE.equals(c.getOperator())) {
				criteria.add(Restrictions.like(propertyName, (String) c.getValue(), MatchMode.ANYWHERE));
			} else if (Operator.RLIKE.equals(c.getOperator())) {
                criteria.add(Restrictions.like(propertyName, (String) c.getValue(), MatchMode.START));
            } else if (Operator.LLIKE.equals(c.getOperator())) {
                criteria.add(Restrictions.like(propertyName, (String) c.getValue(), MatchMode.END));
            } else if (Operator.NE.equals(c.getOperator())) {
				criteria.add(Restrictions.or(Restrictions.ne(propertyName, c.getValue()),
						Restrictions.isNull(propertyName)));
			} else if (Operator.LT.equals(c.getOperator())) {
				criteria.add(Restrictions.lt(propertyName, c.getValue()));
			} else if (Operator.GT.equals(c.getOperator())) {
				criteria.add(Restrictions.gt(propertyName, c.getValue()));
			} else if (Operator.GE.equals(c.getOperator())) {
				criteria.add(Restrictions.ge(propertyName, c.getValue()));
			} else if (Operator.LE.equals(c.getOperator())) {
				criteria.add(Restrictions.le(propertyName, c.getValue()));
			} else if (Operator.IN.equals(c.getOperator())) {
				Collection<?> colection = (Collection<?>) c.getValue();
				criteria.add(Restrictions.in(propertyName, colection));
			}
		}
		return criteria;
	}

	private <T> void addCriterionOrderByFitler(Criteria criteria, IFilter filter) {
		if (filter == null) {
			return;
		}
		for (OrderBy order : filter.getOrders()) {
			criteria.addOrder(convert(order));
		}
	}

	private Order convert(OrderBy orderBy) {
		if (orderBy.isAscending()) {
			return Property.forName(orderBy.getPropertyName()).asc();
		}
		return Property.forName(orderBy.getPropertyName()).desc();
	}

	private <T> String getIdName(Class<T> entityClass) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	private int countHqlResultByFilter(IFilter filter, String hql, Object[] values) {
		String countHql = prepareCountHql(hql);
		try {
			Long count = findUniqueByHqlByFilter(filter, countHql, values);
			return count.intValue();
		} catch (Exception e) {
			throw new AppException("hql can't be auto count, hql is: " + countHql, e);
		}
	}

	private <T> T findUniqueByHqlByFilter(IFilter filter, String countHql, Object[] values) {
		return (T) this.createQueryByFilter(filter, countHql, values).uniqueResult();
	}

	public Query createQueryByFilter(IFilter filter, String outerHql, Object... values) {
		FilterHql2 fh = new FilterHql2(filter);

		String hqlBeforeOrder = "";
		String orders = "";
		int index = outerHql.indexOf("order by");
		if (index > 0) {
			orders = " " + outerHql.substring(index);
			hqlBeforeOrder = outerHql.substring(0, index);
		} else {
			hqlBeforeOrder = outerHql;
		}

		String hql = hqlBeforeOrder + fh.getHql() + orders;

		Query query = this.createQuery(hql, values);

		String[] names = fh.getParamNames();
		Object[] paramValues = fh.getValues();
		for (int i = 0; i < names.length; i++) {
			addNamedParameter(query, names[i], paramValues[i]);
		}
		return query;
	}

	private void addNamedParameter(Query queryObject, String paramName, Object value) {
		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection<?>) value);
		} else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		} else {
			queryObject.setParameter(paramName, value);
		}
	}

	protected int countHqlResult(final String hql, final Object... values) {
		String countHql = prepareCountHql(hql);
		try {
			Long count = findUniqueByHql(countHql, values);
			return count.intValue();
		} catch (Exception e) {
			throw new AppException("hql can't be auto count, hql is: " + countHql, e);
		}
	}

	private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;
		fromHql = "from " + fromHql.substring(fromHql.indexOf("from") + 4);
		if (fromHql.indexOf("order by") > 0) {
			fromHql = fromHql.substring(0, fromHql.indexOf("order by"));
		}

		return "select count(*) " + fromHql;
	}

}

class FilterHql2 {
	private String hql;

	private List<String> paramNames = new ArrayList<String>();

	private List<Object> values = new ArrayList<Object>();

	public FilterHql2(IFilter outerFilter) {
		IFilter filter = null;
		if (outerFilter == null) {
			filter = new Filter();
		} else {
			filter = outerFilter;
		}
		StringBuffer filterHql = new StringBuffer(" ");
		for (IFilterEntry c : filter.entrySet()) {
			Object value = c.getValue();
			if (value == null) {
				continue;
			}
			String key = c.getKey();
			String name = key.replace(".", "_");
			if (paramNames.contains(name)) {
				name = name + System.nanoTime();
			}
			if(c.getOperator().equals(Operator.OR)) {//或操作
				@SuppressWarnings("rawtypes")
				Collection orValues = (Collection)c.getValue();
				if(!orValues.isEmpty()){
					filterHql.append("	and (");
					StringBuffer orHql = new StringBuffer();
					for(Object each : orValues){
						orHql.append(" or ").append(c.getKey()).append(" = ").append(each);
					}
					filterHql.append(orHql.toString().substring(4)).append(" )");
				}
			}else{
				filterHql.append("	and ").append(c.getKey()).append(" ").append(c.getOperator().toString()).append(" :")
						.append(name);
				paramNames.add(name);
				if (c.getOperator().equals(Operator.LIKE)) {
					value = "%" + value + "%";
				}
				if(c.getOperator().equals(Operator.RLIKE)) {
					value = value + "%";
				}
				if(c.getOperator().equals(Operator.LLIKE)) {
					value = "%" + value;
				}
				values.add(value);
			}
		}
		this.hql = filterHql.toString();
	}

	public String getHql() {
		return hql;
	}

	public String[] getParamNames() {
		String[] result = new String[this.paramNames.size()];
		this.paramNames.toArray(result);
		return result;
	}

	public Object[] getValues() {
		return this.values.toArray();
	}

}
