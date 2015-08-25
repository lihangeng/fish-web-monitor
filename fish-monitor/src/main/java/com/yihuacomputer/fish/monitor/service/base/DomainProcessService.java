package com.yihuacomputer.fish.monitor.service.base;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
import com.yihuacomputer.fish.api.monitor.alarm.IProcess;
import com.yihuacomputer.fish.monitor.entity.alarm.SysProcess;
import com.yihuacomputer.fish.monitor.service.api.IDomainProcessService;

public abstract class DomainProcessService implements IDomainProcessService {
	public SysProcess make() {
		return new SysProcess(this);
	}

	public IPageResult<IProcess> page(int offset, int limit, IFilter filter) {
		List<IProcess> lists = new ArrayList<IProcess>();
		EntityUtils.convert(list(filter), lists);
		return new PageResult<IProcess>(lists, offset, limit);
	}
	
	@Override
    public IPageResult<IIllegalProcess> pageSchindlerAlarm(int offset, int limit,IFilter filter){
	    List<IIllegalProcess> lists = new ArrayList<IIllegalProcess>();
        EntityUtils.convert(listSchindlerAlarm(filter), lists);
        return new PageResult<IIllegalProcess>(lists, offset, limit);
    }
}
