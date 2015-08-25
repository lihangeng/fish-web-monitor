/**
 *
 */
package com.yihuacomputer.common.util;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.IPageResult;

/**
 * @author xuxigang
 *
 */
public class EntityUtils {
	public static <IT> List<IT> convert(List<? extends IT> lists){
		List<IT> result = new ArrayList<IT>();
		for(IT each : lists){
			result.add(each);
		}
		return result;
	}

	public static <IT> IPageResult<IT> convert(IPageResult<? extends IT> pageResult){
		PageResult<IT> page = new PageResult<IT>();
		page.setTotal(pageResult.getTotal());
		List<IT> data =  new ArrayList<IT>();
		for(IT item : pageResult.list()) {
			data.add(item);
		}
		page.setData(data);
		return page;
	}

	public static <IT> void convert(Iterable<? extends IT> lists,List<IT> result){
		for(IT each : lists){
			result.add(each);
		}
	}

	public static <IT> List<IT> convert(Iterable<IT> lists){
		List<IT> result = new ArrayList<IT>();
		for(IT each : lists){
			result.add(each);
		}
		return result;
	}

}
