package com.yihuacomputer.fish.api.monitor.filter;

import java.util.List;

import org.springframework.context.MessageSource;

import com.yihuacomputer.fish.api.monitor.report.IClassifyReport;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;

public interface IClassifyModStatusFilter {
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

	public void setSubOrg(List<Long> subOrg);

	/**
	 * 过滤模块信息
	 *
	 * @param device
	 * @param trans
	 * @return
	 */
	public IClassifyReport filterMod(IDeviceReport trans,MessageSource messageSourceRef);



	/**
	 * 获取页面现有设备
	 * @return
	 */
	public List<String> getModDeviceList();

	/**
	 * 设置页面现有设备
	 * @param deviceList
	 */
	public void setModDeviceList(List<String> deviceList);

	/**
	 * 获取页面显示条数
	 * @return
	 */
	public int getModLimit();

	/**
	 * 设置页面显示条数
	 * @param limit
	 */
	public void setModLimit(int limit);

	/**
	 * 添加设备
	 * @param terminalId
	 */
	public void modAddDevice(String terminalId);

	/**
	 * 删除设备
	 * @param terminalId
	 */
	public void modRemoveDevice(String terminalId);

	public void netAddDevice(String terminalId);

	public void netRemoveDevice(String terminalId);

	public void boxAddDevice(String terminalId);

	public void boxRemoveDevice(String terminalId);

	public List<String> getNetDeviceList();

	public void setNetDeviceList(List<String> netDeviceList);

	public List<String> getBoxDeviceList();

	public void setBoxDeviceList(List<String> boxDeviceList);




	public int getNetLimit();

	public void setNetLimit(int netLimit);

	public int getBoxLimit();

	public void setBoxLimit(int boxLimit);

}
