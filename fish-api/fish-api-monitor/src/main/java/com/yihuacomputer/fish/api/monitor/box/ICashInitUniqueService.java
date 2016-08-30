package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.fish.api.person.IOrganization;

public interface ICashInitUniqueService {
	ICashInitUnique make();
	ICashInitUnique save(ICashInitUnique cashInitUnique);
	ICashInitUnique update(ICashInitUnique cashInitUnique);
	void remove(ICashInitUnique cashInitUnique);
	List<ICashInitUnique> list(IFilter filter);
	ICashInitUnique getByTerminalId(String terminalId);
	/**
	 * 获取指定机构超过指定天数未清机加钞的设备列表
	 * @param org
	 * @param cashInitDays
	 * @return
	 */
	List<ICashInitUnique> getCashInitByOrg(IOrganization org,int cashInitDays);
	/**
	 * 获取指定机构设备的加钞信息集合
	 * @param org
	 * @return
	 */
	public Map<String, ICashInitUnique> getCashInitMap(IOrganization org);
}
