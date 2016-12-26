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
	
	/**
	 * @param idc
	 */
	public void setStatus(DeviceStatus idc);
	
	/**
	 * @param code
	 */
	public void setCode(String code);
	
	/**
	 * @param cards
	 */
	public void setCards(int cards);
	
	/**
	 * 获取硬件故障码
	 * @return
	 */
	public String getHwCode();
	
	/**
	 * @param hwCode
	 */
	public void setHwCode(String hwCode);

}
