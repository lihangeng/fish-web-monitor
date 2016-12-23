package com.yihuacomputer.fish.api.monitor.filter;

import java.util.List;

import org.springframework.context.MessageSource;

import com.yihuacomputer.fish.api.monitor.report.IClassifyReport;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;

/**
 * @author YiHua
 *
 */
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

	/**
	 * @param subOrg
	 */
	public void setSubOrg(List<Long> subOrg);

	/**
	 * 过滤模块信息
	 *
	 * @param trans
	 * @param messageSourceRef
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

	/**
	 * @param terminalId
	 */
	public void netAddDevice(String terminalId);

	/**
	 * @param terminalId
	 */
	public void netRemoveDevice(String terminalId);

	/**
	 * @param terminalId
	 */
	public void boxAddDevice(String terminalId);

	/**
	 * @param terminalId
	 */
	public void boxRemoveDevice(String terminalId);

	/**
	 * @return
	 */
	public List<String> getNetDeviceList();

	/**
	 * @param netDeviceList
	 */
	public void setNetDeviceList(List<String> netDeviceList);

	/**
	 * @return
	 */
	public List<String> getBoxDeviceList();

	/**
	 * @param boxDeviceList
	 */
	public void setBoxDeviceList(List<String> boxDeviceList);




	/**
	 * @return
	 */
	public int getNetLimit();

	/**
	 * @param netLimit
	 */
	public void setNetLimit(int netLimit);

	/**
	 * @return
	 */
	public int getBoxLimit();

	/**
	 * @param boxLimit
	 */
	public void setBoxLimit(int boxLimit);

}
