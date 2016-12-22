package com.yihuacomputer.common.util;


import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.IPageResult;

/**
 * 
 *
 * @param <T>
 */
public class PageResult<T> implements IPageResult<T> {
	
	private int total;
	private List<T> data;

	public PageResult() {	
	}
	
	/**
	 * @param all
	 * @param offset
	 * @param limit
	 */
	public PageResult(List<T> all, int offset, int limit) {
		total = all.size();
		data = new ArrayList<T>();
		int end = offset + limit;
		end = end < total ? end : total;
		for(int index = offset; index < end; index++) {
			data.add(all.get(index));
		}
	}

	/**
	 * @param total
	 * @param data
	 */
	public PageResult(int total, List<T> data) {
		this.total = total;
		this.data = data;
	}
	
	@Override
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public List<T> list() {
		return data;
	}
	
	public void setData(List<T> data) {
		this.data = data;
	}

}
