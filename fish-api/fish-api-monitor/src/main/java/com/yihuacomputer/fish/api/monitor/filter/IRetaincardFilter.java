package com.yihuacomputer.fish.api.monitor.filter;

import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;

/**
 * @author YiHua
 *
 */
public interface IRetaincardFilter {

	/**
	 * 机构ID
	 *
	 * @return
	 */
	public String getOrgId();

	/**
	 * 机构ID
	 *
	 * @param orgId
	 */
	public void setOrgId(String orgId);

	/**
	 * 设备号
	 *
	 * @return
	 */
	public String getTerminalId();

	/**
	 * 设备号
	 *
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * 下级机构集合
	 *
	 * @return
	 */
	public List<Long> getSubOrg();

	/**
	 * @param subOrg
	 */
	public void setSubOrg(List<Long> subOrg);

	/**
	 * 过滤吞卡信息
	 *
	 * @param device
	 * @param trans
	 * @return
	 */
	public boolean filterRetaincard(IDevice device, IRetaincard trans);

	/**
	 * @return
	 */
	public int getCardRetainNum();

	/**
	 * @param cardRetainNum
	 */
	public void setCardRetainNum(int cardRetainNum);

}
