package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * IDC读卡器模块状态信息
 * @author YiHua
 *
 */
public interface IStatusIdc {
	
	/**
	 * 获取硬件主状态
	 * @return 硬件主状态
	 */
	public DeviceStatus getStatus();

	/**
	 * 获取状态代码
	 * @return 状态代码
	 */
	public String getCode();
	
	/**
	 * 获取吞卡张数
	 * @return 吞卡张数
	 */
	public int getCards();	
	
	public void setStatus(DeviceStatus idc);
	
	public void setCode(String code);
	
	public void setCards(int cards);
	
	/**
	 * 获取硬件故障码
	 * @return
	 */
	public String getHwCode();
	
	public void setHwCode(String hwCode);

}
