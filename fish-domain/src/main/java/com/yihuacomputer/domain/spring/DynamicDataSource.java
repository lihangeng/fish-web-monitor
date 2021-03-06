package com.yihuacomputer.domain.spring;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author YiHua
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseContextHolder.getDataSource();
	}

}