package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * CDM取款模块状态信息
 * @author YiHua
 *
 */
public interface IStatusCdm {
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
	 * @param cdm
	 */
	public void setStatus(DeviceStatus cdm);
   
   /**
	 * @param code
	 */
	public void setCode(String code);

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
