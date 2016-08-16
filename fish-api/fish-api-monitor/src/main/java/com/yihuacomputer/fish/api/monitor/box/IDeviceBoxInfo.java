package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;

public interface IDeviceBoxInfo {
	public long getId() ;

	public void setId(long id);

	/**
	 * 设备
	 * @return
	 */
	public IDevice getDeviceId();

	/**
	 * 设备
	 * @param deviceId
	 */
	public void setDeviceId(IDevice deviceId);

	/**
	 * 存款预警上线
	 * @return
	 */
	public long getMaxAlarm();

	/**
	 * 存款预警上线
	 * @param maxAlarm
	 */
	public void setMaxAlarm(long maxAlarm);

	/**
	 * 取款预警下限
	 * @return
	 */
	public long getMinAlarm();

	/**
	 * 取款预警下限
	 * @param minAlarm
	 */
	public void setMinAlarm(long minAlarm);

	/**
	 * 钞箱是否变化
	 * @return
	 */
	public boolean isBoxChange();

	/**
	 * 钞箱是否变化
	 * @param boxChange
	 */
	public void setBoxChange(boolean boxChange);
	

	/**
	 * 设备钞箱明细
	 * @return
	 */
	public List<IDeviceBoxDetailInfo> getDeviceBoxDetails();

	/**
	 * 设备钞箱明细
	 * @param deviceBoxDetails
	 */
	public void setDeviceBoxDetails(List<IDeviceBoxDetailInfo> deviceBoxDetails) ;
	
	public void add(IDeviceBoxDetailInfo deviceBoxDetail);
	
	/**
	 * 默认取款值
	 * @return
	 */
	public long getDefaultBill();

	/**
	 * 默认取款值
	 * @param defaultBill
	 */
	public void setDefaultBill(long defaultBill);

	/**
	 * 默认存款值
	 * @return
	 */
	public long getDefaultCashIn();

	/**
	 * 默认存款值
	 * @param defaultCashIn
	 */
	public void setDefaultCashIn(long defaultCashIn);
}
