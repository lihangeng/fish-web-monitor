package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;

/**
 * 设备钞箱总信息
 * @author GQ
 *
 */
public interface IDeviceBoxInfo {
	/**
	 * @return
	 */
	public long getId() ;

	/**
	 * @param id
	 */
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
	
	/**
	 * @param deviceBoxDetail
	 */
	public void add(IDeviceBoxDetailInfo deviceBoxDetail);
	
	/**
	 * 默认最大取款值
	 * @return
	 */
	public long getDefaultBill();

	/**
	 * 默认最大取款值
	 * @param defaultBill
	 */
	public void setDefaultBill(long defaultBill);

	/**
	 * 默认最大存款值
	 * @return
	 */
	public long getDefaultCashIn();

	/**
	 * 默认最大存款值
	 * @param defaultCashIn
	 */
	public void setDefaultCashIn(long defaultCashIn);
	
	/**
	 * 当前取款箱金额
	 * @return
	 */
	public long getBillValue();

	/**
	 * 当前取款箱金额
	 * @param billValue
	 */
	public void setBillValue(long billValue);

	/**
	 * 当前存款箱金额
	 * @return
	 */
	public long getCashInValue();

	/**
	 * 当前存款箱金额
	 * @param cashInValue
	 */
	public void setCashInValue(long cashInValue);
}
