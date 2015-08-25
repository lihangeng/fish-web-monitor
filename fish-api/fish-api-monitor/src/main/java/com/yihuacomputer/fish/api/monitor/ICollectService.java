package com.yihuacomputer.fish.api.monitor;

import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
import com.yihuacomputer.fish.api.monitor.alarm.ISoftwareFailure;
import com.yihuacomputer.fish.api.monitor.business.ICashInit;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoney;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.ISettlement;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.business.IUncommonTrans;
import com.yihuacomputer.fish.api.monitor.hardware.IHardware;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.report.IRuntimeInfo;
import com.yihuacomputer.fish.api.monitor.report.IWorkUnit;
import com.yihuacomputer.fish.api.monitor.software.ISoftware;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IXfsPropertise;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

/**
 * 设备信息采集服务
 * 
 * @author YiHua
 * @since 2012/2/6
 * @version 1.0
 */

public interface ICollectService {

	/**
	 * 注册信息收集监听器
	 * 
	 * @param listener 采集监听器
	 */
	public void setClollectListener(ICollectListener listener);
	
	/**
	 * 收集服务初始化
	 */
	public void init();

	/**
	 * 收集服务关闭
	 * */
	public void close();

	/**
	 * 设备安装注册
	 * @param terminalId
	 * @param deviceRegister
	 */
	public void collectSetup(String terminalId,IDeviceRegister deviceRegister);

	/**
	 * 设备开机签到
	 * @param terminalId
	 * @param deviceRegister
	 */
	public void collectBootSign(String terminalId, IDeviceRegister deviceRegister);	

	
    /**
     * 收集设备开机上送系统主机硬件信息
     * 
     * @param terminalId 设备ID
     * @param hardware 硬件信息
     */
    public void collectDeviceHardware(String terminalId, IHardware hardware);

    /**
     *收集设备操作系统软件信息
     *
     * @param terminalId 设备ID
     * @param software 软件信息
     */    
    public void collectDeviceSoftware(String terminalId, ISoftware software);

    
	/**
	 * 收集系统软件报错信息
	 * 
	 * @param terminalId 设备ID
	 * @param failure 故障信息
	 */
	public void collectSoftwareFailure(String terminalId, ISoftwareFailure failure);

	/**
	 * 收集设备非百名但进程信息报警
	 * 
	 * @param terminalId 设备ID
	 * @param process 进程信息
	 */
	public void collectSchindlerAlarm(String terminalId, List<IIllegalProcess> processList);

	/**
	 * 收集ATMC应用状态切换信息
	 * 
	 * @param terminalId 设备ID
	 * @param runInfo 运行信息
	 */
	public void collectATMCRunInfo(String terminalId, IRunInfo runInfo);

		
	/**
	 * 收集ATMC加钞信息
	 * 
	 * @param terminalId 设备ID
	 * @param cashInit 加钞信息
	 */
	public void collectCashInit(String terminalId, ICashInit cashInit);

	/**
	 * 收集ATMC清机结算信息
	 * 
	 * @param terminalId 设备ID
	 * @param settlement 结账信息
	 */
	public void collectSettlement(String terminalId, ISettlement settlement);

	/**
	 * 收集ATMC吞卡信息
	 * @param terminalId settlement
	 * @param retainCard 吞卡信息
	 */
	public void collectATMCRetainCard(String terminalId, IRetaincard retainCard);

	/**
	 * 收集设备硬件模块属性信息
	 * @param termianlId settlement
	 * @param xfsPro 设备属信息
	 */
	public void collectModulePropertise(String terminalId,IXfsPropertise xfsPro);
	
	/**
	 * 收集设备硬件模块状态信息
	 * 
	 * @param terminalId 设备ID
	 * @param xfsStatus 设备状态信息
	 */
	public void collectModuleStatus(String terminalId, IXfsStatus xfsStatus);
	
	/**
	 * 收集设备网络故障信息
	 * @param terminalId
	 * @param xfsStatus
	 */
	public void collectNetError(String terminalId, IXfsStatus xfsStatus);
	/**
	 * 收集ATMC交易信息
	 * 
	 * @param terminalId
	 * @param transaction
	 */
	public void collectATMCTransaction(String terminalId,ITransaction transaction);

	/**
	 * 添加数据加工单元
	 * @param unit 数据加工单元
	 */
	public void addWorkUnit(IWorkUnit unit);
	
	/**
	 * 获取设备动态信息
	 * @param terminalId 设备ID
	 * @return 设备监控信息
	 */
	public IDeviceReport getDeviceReport(String terminalId);
	
	/**
	 * 添加设备时初始化表中对应的记录
	 * @param terminalId
	 */
	public void initDeviceCollect(IDevice device);
	
	/**
	 * 删除设备时删除添加的数据
	 * @param device
	 */
	public void deleteDevice(IDevice device);
	
	/**
	 * 收集ATM运行信息
	 * @param terminalId
	 * @param runtimeInfo
	 */
	public void collectRumtimeInfo(String terminalId,IRuntimeInfo runtimeInfo);
	
	/**
	 * 收集假币疑问币信息
	 * 
	 * @param terminalId
	 * @param transaction
	 */
	public void collectATMCCounterFeitMoney(String terminalId,ICounterFeitMoney counterFeitMoney);

	/**
	 * 收集异常交易信息
	 * 
	 * @param terminalId
	 * @param transaction
	 */
	public void collectATMCUncommonTrans(String terminalId,IUncommonTrans uncommonTrans);
}
