package com.yihuacomputer.fish.monitor.entity.filter;

import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.filter.IRetaincardFilter;

/**
 * 吞卡监控条件
 *
 * @author pengwenchao
 *
 */
public class RetaincardFilter implements IRetaincardFilter {
	/**
	 * 机构ID
	 */
	private String orgId;
	/**
	 * 设备号
	 */
	private String terminalId;

	/**
	 * 下级机构集合
	 */
	private List<Long> subOrg;


	private int cardRetainNum;

	@Override
	public String getOrgId() {
		return this.orgId;
	}

	@Override
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Override
	public String getTerminalId() {
		return this.terminalId;
	}

	@Override
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public List<Long> getSubOrg() {
		return this.subOrg;
	}

	@Override
	public void setSubOrg(List<Long> subOrg) {
		this.subOrg = subOrg;
	}

	@Override
	public boolean filterRetaincard(IDevice device, IRetaincard retaincard) {

		// 设备号不存在
		if (device == null) {
			return false;
		}

		// 机构不匹配
		if (!this.subOrg.contains(Long.parseLong(device.getOrganization().getGuid()))) {
			return false;
		}

		if (this.terminalId == null || "".equals(this.terminalId) || device.getTerminalId().equals(this.terminalId)) {
			return true;
		}

		return false;
	}

	@Override
	public int getCardRetainNum() {
		return cardRetainNum;
	}

	@Override
	public void setCardRetainNum(int cardRetainNum) {
		this.cardRetainNum = cardRetainNum;
	}








}
