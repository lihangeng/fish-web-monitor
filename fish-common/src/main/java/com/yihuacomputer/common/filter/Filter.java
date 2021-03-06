package com.yihuacomputer.common.filter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.Operator;
import com.yihuacomputer.common.OrderBy;
import com.yihuacomputer.common.exception.AppException;

/**
 * 过滤器
 * @author GQ
 *
 */
public class Filter implements IFilter {

	private static Logger logger = LoggerFactory.getLogger(Filter.class);
	private Set<IFilterEntry> entrySet = new HashSet<IFilterEntry>();
	private Set<OrderBy> orderBys = new LinkedHashSet<OrderBy>();

	private Filter filterTmp = this;

	@Override
	public void addFilterEntry(IFilterEntry entry) {
		this.entrySet.add(entry);
	}

	@Override
	public Set<IFilterEntry> entrySet() {
		return this.entrySet;
	}

	@Override
	public Object getValue(String key) {
		for (IFilterEntry entry : entrySet) {
			if (entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}
		return null;
	}


	@Override
	public boolean isEmpty() {
		return entrySet.isEmpty();
	}


	@Override
	public Set<String> keys() {
		Set<String> keys = new HashSet<String>();
		for (IFilterEntry entry : entrySet) {
			keys.add(entry.getKey());
		}
		return keys;
	}


	@Override
	public void addOrder(OrderBy order) {
		orderBys.add(order);
	}

	@Override
	public Set<OrderBy> getOrders() {
		return this.orderBys;
	}

	@Override
	public boolean isMatch(Object object) {
		try{
			for (IFilterEntry entry : this.entrySet()) {
				if(entry.getValue() == null) {
					continue;
				}
				Object value = null;
				try{
					value = PropertyUtils.getProperty(object, entry.getKey());
				}catch(NestedNullException ex){
					logger.error("PropertyUtils.getProperty(object, entry.getKey()):["+ex+"]");
					return false;
				}
				if (entry.getOperator().equals(Operator.EQ)) {
					if(value == entry.getValue()) {
						continue;
					}
					else if(value == null||!value.toString().equals(entry.getValue().toString())){
						return false;
					}
				}
				else if(entry.getOperator().equals(Operator.LIKE)){
					if(value instanceof String&&!StringUtils.contains(value.toString(), entry.getValue().toString())){
						return false;
					}
				}
				else if(entry.getOperator().equals(Operator.NE)) {
					if(value == entry.getValue() || (value != null && value.toString().equals(entry.getValue().toString()))) {
						return false;
					}
				}
			}
			return true;
		}
		catch (IllegalAccessException e) {
			throw new AppException("Illegal interview" + e.getMessage(),e);
		} catch (InvocationTargetException e) {
			throw new AppException("System error!" + e.getMessage(),e);
		} catch (NoSuchMethodException e) {
			throw new AppException("Can't find Method" + e.getMessage(),e);
		}
	}

	/* (non-Javadoc)
	 * @see com.yihuacomputer.common.IFilter#filter(java.util.List)
	 */
	@Override
	public <T> List<T> filter(List<T> data) {
		List<T> result = new ArrayList<T>();
		for(T item : data) {
			if(isMatch(item)) {
				result.add(item);
			}
		}
		return result;
	}

	@Override
	public int getEntrySize(){
		return this.entrySet.size();
	}

	@Override
	public boolean equals(Object object) {
		IFilter filter = null;
		if(object instanceof IFilter){
			filter = (IFilter)object;
		}else{
			return false;
		}

		if(this.getEntrySize() != filter.getEntrySize()){
			return false;
		}

		for (IFilterEntry entry : this.entrySet()) {
			IFilterEntry each = filter.getFilterEntry(entry.getKey());
			if(each == null){
				return false;
			}else{
				if(!entry.equals(each)){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public IFilterEntry getFilterEntry(String key) {
		for(IFilterEntry entry : this.entrySet()){
			if(entry.getKey().equals(key)){
				return entry;
			}
		}
		return null;
	}

	@Override
	public IFilter gt(String propertyName, Object value) {
		filterTmp.addFilterEntry(FilterFactory.gt(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter ge(String propertyName, Object value) {
		filterTmp.addFilterEntry(FilterFactory.ge(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter lt(String propertyName, Object value) {
		filterTmp.addFilterEntry(FilterFactory.lt(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter le(String propertyName, Object value) {
		filterTmp.addFilterEntry(FilterFactory.le(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter ne(String propertyName, Object value) {
		filterTmp.addFilterEntry(FilterFactory.ne(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter eq(String propertyName, Object value) {
		filterTmp.addFilterEntry(FilterFactory.eq(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter like(String propertyName, String value) {
		filterTmp.addFilterEntry(FilterFactory.like(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter rlike(String propertyName, String value) {
		filterTmp.addFilterEntry(FilterFactory.rlike(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter llike(String propertyName, String value) {
		filterTmp.addFilterEntry(FilterFactory.llike(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter in(String propertyName, Collection<?> value) {
		filterTmp.addFilterEntry(FilterFactory.in(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter or(String propertyName, Collection<?> value) {
		filterTmp.addFilterEntry(FilterFactory.or(propertyName, value));
		return filterTmp;
	}

	@Override
	public IFilter descOrder(String propertyName) {
		filterTmp.addOrder(new OrderBy(propertyName, OrderBy.DESC));
		return filterTmp;
	}

	@Override
	public IFilter order(String propertyName) {
		filterTmp.addOrder(new OrderBy(propertyName, OrderBy.ASC));
		return filterTmp;
	}



}
