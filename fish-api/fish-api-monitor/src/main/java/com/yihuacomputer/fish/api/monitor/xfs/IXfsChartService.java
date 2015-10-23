package com.yihuacomputer.fish.api.monitor.xfs;

import java.util.List;

import com.yihuacomputer.common.IFilter;

public interface IXfsChartService {

	/**
	 * 所有设备信息
	 * @param filter(orgFlag)
	 * 隐式条件device.status  opening
	 * @return
	 */
	public List<Object> getAllDeviceList(IFilter filter);
	/**
	 * 设备运行总状态分为两类，当前取正常设备信息
	 * 1.四种指标均为heathly
	 * 		a.运行为 heathly,customer
	 * 		b.钞箱为 heathly
	 * 		c.模块为 heathly
	 * 		d.网络为 heathly
	 * 2.四种指标有任何一个部位heathly
	 * @param filter(orgFlag)
	 * 隐式条件device.status  opening
	 * @return
	 */
	public List<Object> getDeviceSummaryRunInfo(IFilter filter);
	/**
	 * 获取当前生产环境应用的运行信息
	 *
	 * @param filter(orgFlag)
	 * 隐式条件device.status  opening
	 * @return
	 */
	public List<Object> getDeviceAppRunInfo(IFilter filter);
	/**
	 * 获取当前生产环境钞箱的运行信息
	 * @param filter(orgFlag)
	 * 隐式条件device.status  opening
	 * @return
	 */
	public List<Object> getDeviceBoxRunInfo(IFilter filter);
	/**
	 * 获取当前生产环境模块的运行信息
	 * @param filter(orgFlag)
	 * 隐式条件device.status  opening
	 * @return
	 */
	public List<Object> getDeviceModRunInfo(IFilter filter);
	/**
	 *  获取当前生产环境网络的运行信息
	 * @param filter(orgFlag)
	 * 隐式条件device.status  opening
	 * @return
	 */
	public List<Object> getDeviceNetRunInfo(IFilter filter);
}
