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

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.Operator;
import com.yihuacomputer.common.OrderBy;
import com.yihuacomputer.common.exception.AppException;

public class Filter implements IFilter {

	private Set<IFilterEntry> entrySet = new HashSet<IFilterEntry>();
	private Set<OrderBy> orderBys = new LinkedHashSet<OrderBy>();

	private Filter filter = this;

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
					return false;
				}
				if (entry.getOperator().equals(Operator.EQ)) {
					if(value == entry.getValue()) {
						continue;
					}
					else if(value == null && entry.getValue() != null) {
						return false;
					}else if(!value.toString().equals(entry.getValue().toString())){
						return false;
					}
				}
				else if(entry.getOperator().equals(Operator.LIKE)){
					if(value instanceof String){
						if(!StringUtils.contains(value.toString(), entry.getValue().toString())){
							return false;
						}
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
			throw new AppException("Illegal interview" + e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new AppException("System error!" + e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new AppException("Can't find Method" + e.getMessage());
		}
	}

	public <T> List<T> filter(List<T> data) {
		List<T> result = new ArrayList<T>();
		for(T item : data) {
			if(isMatch(item)) {
				result.add(item);
			}
		}
		return result;
	}

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
		filter.addFilterEntry(FilterFactory.gt(propertyName, value));
		return filter;
	}

	@Override
	public IFilter ge(String propertyName, Object value) {
		filter.addFilterEntry(FilterFactory.ge(propertyName, value));
		return filter;
	}

	@Override
	public IFilter lt(String propertyName, Object value) {
		filter.addFilterEntry(FilterFactory.lt(propertyName, value));
		return filter;
	}

	@Override
	public IFilter le(String propertyName, Object value) {
		filter.addFilterEntry(FilterFactory.le(propertyName, value));
		return filter;
	}

	@Override
	public IFilter ne(String propertyName, Object value) {
		filter.addFilterEntry(FilterFactory.ne(propertyName, value));
		return filter;
	}

	@Override
	public IFilter eq(String propertyName, Object value) {
		filter.addFilterEntry(FilterFactory.eq(propertyName, value));
		return filter;
	}

	@Override
	public IFilter like(String propertyName, String value) {
		filter.addFilterEntry(FilterFactory.like(propertyName, value));
		return filter;
	}

	@Override
	public IFilter rlike(String propertyName, String value) {
		filter.addFilterEntry(FilterFactory.rlike(propertyName, value));
		return filter;
	}

	@Override
	public IFilter llike(String propertyName, String value) {
		filter.addFilterEntry(FilterFactory.llike(propertyName, value));
		return filter;
	}

	@Override
	public IFilter in(String propertyName, Collection<?> value) {
		filter.addFilterEntry(FilterFactory.in(propertyName, value));
		return filter;
	}

	@Override
	public IFilter or(String propertyName, Collection<?> value) {
		filter.addFilterEntry(FilterFactory.or(propertyName, value));
		return filter;
	}

	@Override
	public IFilter descOrder(String propertyName) {
		filter.addOrder(new OrderBy(propertyName, OrderBy.DESC));
		return filter;
	}

	@Override
	public IFilter order(String propertyName) {
		filter.addOrder(new OrderBy(propertyName, OrderBy.ASC));
		return filter;
	}



}
