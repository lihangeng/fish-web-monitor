package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.fish.api.person.IOrganization;

public interface ICashInitUniqueService {
	ICashInitUnique make();
	ICashInitUnique save(ICashInitUnique cashInitUnique);
	ICashInitUnique update(ICashInitUnique cashInitUnique);
	void remove(ICashInitUnique cashInitUnique);
	List<ICashInitUnique> list(IFilter filter);
	ICashInitUnique getByTerminalId(String terminalId);
	List<ICashInitUnique> getCashInitByOrg(IOrganization org,int cashInitDays);
}
