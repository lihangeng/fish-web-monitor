package com.yihuacomputer.domain.mem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;

/**
 *
 * @author xuxigang
 * @version
 * @since
 * @date 2010-8-7
 */
public class BaseMemoryService {
	private static final AtomicLong atomicLog = new AtomicLong();

	public long nextId() {
		return atomicLog.incrementAndGet();
	}

	@SuppressWarnings("unchecked")
	public <IT, T extends IT> T interface2Entity(IT it) {
		return (T) it;
	}

	public boolean isMacth(Object object, IFilter filter) {
		return filter.isMatch(object);
	}

	public <IT> List<IT> filter(List<? extends IT> data, IFilter outerFilter) {
		IFilter filter = null;
	    if(outerFilter == null){
	        filter = new Filter();
		}else{
		    filter = outerFilter;
		}
		List<? extends IT> data2 = filter.filter(data);
		List<IT> result = new ArrayList<IT>();
		for(IT item : data2) {
			result.add(item);
		}
		return result;
	}

}
