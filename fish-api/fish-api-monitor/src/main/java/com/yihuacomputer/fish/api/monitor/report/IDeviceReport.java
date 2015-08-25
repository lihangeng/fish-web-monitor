package com.yihuacomputer.fish.api.monitor.report;

import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
import com.yihuacomputer.fish.api.monitor.alarm.IProcess;
import com.yihuacomputer.fish.api.monitor.alarm.ISoftwareFailure;
import com.yihuacomputer.fish.api.monitor.business.CounterFeitMoneyForms;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoney;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.monitor.software.ISoftware;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
/**
 * 设备监控信息汇总
 * @author YiHua
 *
 */
public interface IDeviceReport {

	public String getDeviceId();

	/**
	 * 设备信息.
	 *
	 * @return
	 */
	public IDevice getDevice();

	/**
	 * 注册信息
	 *
	 * @return
	 */
	public IDeviceRegister getDeviceRegister();

	/**
	 * 系统硬件信息
	 *
	 * @return
	 */
	public IHardware getHardware();

	/**
	 * 系统软件信息
	 *
	 * @return
	 */
	public ISoftware getSoftware();

	/**
	 * 运行状态
	 *
	 * @return
	 */
	public IRunInfo getRunInfo();

	public List<IProcess> listSchindlerAlarm();

	public List<ISoftwareFailure> listSoftwareFailure(int start, int limit);

	/**
	 * 模块状态信息
	 *
	 * @return
	 */
	public IXfsStatus getXfsStatus();

	/**
	 * 获取交易数据
	 * @return
	 */
	public ITransaction getTransaction();

	/**
	 * 获取白面进程报警信息
	 * @return
	 */
	public List<IIllegalProcess> getProcess();

	/**
	 * 设置白名单进程报警信息
	 * @param process
	 */
	public void setProcess(List<IIllegalProcess> process);

	/**
	 * 获取疑问币假币数据
	 * @return
	 */
	public ICounterFeitMoney getCounterFeitMoney();

	/**
	 * 获取吞卡数据
	 *
	 * @return
	 */
	public IRetaincard getRetaincard();
	
	public List<CounterFeitMoneyForms> getForms();
	
	public void setCounterFeitMoneyForms(List<CounterFeitMoneyForms> forms);
	 
}

