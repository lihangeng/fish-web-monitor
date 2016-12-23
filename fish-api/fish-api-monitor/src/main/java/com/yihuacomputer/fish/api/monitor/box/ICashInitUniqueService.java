package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * @author YiHua
 *
 */
public interface ICashInitUniqueService {
	/**
	 * @return
	 */
	ICashInitUnique make();
	/**
	 * @param cashInitUnique
	 * @return
	 */
	ICashInitUnique save(ICashInitUnique cashInitUnique);
	/**
	 * @param cashInitUnique
	 * @return
	 */
	ICashInitUnique update(ICashInitUnique cashInitUnique);
	/**
	 * @param cashInitUnique
	 */
	void remove(ICashInitUnique cashInitUnique);
	/**
	 * @param filter
	 * @return
	 */
	List<ICashInitUnique> list(IFilter filter);
	/**
	 * @param terminalId
	 * @return
	 */
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
