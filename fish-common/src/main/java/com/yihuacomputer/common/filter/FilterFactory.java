package com.yihuacomputer.common.filter;

import java.util.Collection;

import com.yihuacomputer.common.Operator;


/**
 * 查询条目工厂
 *
 */
public class FilterFactory {

	/**等于
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry eq(String propertyName, Object value) {
		return new FilterEntry(propertyName,value,Operator.EQ);
	}

	/**不等于
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry ne(String propertyName, Object value) {
		return new FilterEntry(propertyName,value,Operator.NE);
	}

	/**大于
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry lt(String propertyName, Object value) {
		return new FilterEntry(propertyName,value,Operator.LT);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry le(String propertyName, Object value) {
		return new FilterEntry(propertyName,value,Operator.LE);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry gt(String propertyName, Object value) {
		return new FilterEntry(propertyName,value,Operator.GT);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry ge(String propertyName, Object value) {
		return new FilterEntry(propertyName,value,Operator.GE);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry like(String propertyName, String value) {
		return new FilterEntry(propertyName,value,Operator.LIKE);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry rlike(String propertyName, String value) {
		return new FilterEntry(propertyName,value,Operator.RLIKE);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry llike(String propertyName, String value) {
		return new FilterEntry(propertyName,value,Operator.LLIKE);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry in(String propertyName, Collection<?> value) {
		return new FilterEntry(propertyName,value,Operator.IN);
	}

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static FilterEntry or(String propertyName, Collection<?> value) {
		return new FilterEntry(propertyName,value,Operator.OR);
	}

}
