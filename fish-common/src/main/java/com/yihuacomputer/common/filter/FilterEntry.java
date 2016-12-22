package com.yihuacomputer.common.filter;

import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.Operator;

/**
 * 查询条目
 * @author xuxigang
 * @version
 * @since  
 * @date 2010-8-8
 */
public class FilterEntry implements IFilterEntry{
	
	private String key;
	private Object value;
	private Operator op;

	/**
	 * @param key
	 * @param value
	 */
	public FilterEntry(String key,Object value){
		this.key =key;
		this.value = value;
		this.op = Operator.EQ;
	}
	
	/**
	 * @param key
	 * @param value
	 * @param op
	 */
	public FilterEntry(String key,Object value,Operator op){
		this.key =key;
		this.value = value;
		this.op = op;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public Object getValue() {
		return this.value;
	}

	@Override
	public Operator getOperator() {
		return this.op;
	}

	@Override
	public boolean equals(Object object) {	
		if(object instanceof IFilterEntry){
			IFilterEntry entry = (IFilterEntry)object;
			if(entry.getKey().equals(this.key) && entry.getValue().equals(this.value) 
					&& entry.getOperator().equals(this.op)){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
